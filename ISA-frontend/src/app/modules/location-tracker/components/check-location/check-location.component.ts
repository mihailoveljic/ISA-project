import { BloodBankService } from './../../../centers/services/blood-bank.service';
import { DeliveryLocation } from './../../models/DeliveryLocation';
import { LocationService } from './../../services/location.service';
import { Component, ViewEncapsulation } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import { fromLonLat } from 'ol/proj';
import { OSM } from 'ol/source';
import { Tile } from 'ol/layer';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import Feature from 'ol/Feature';
import { Point } from 'ol/geom';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';


@Component({
  templateUrl: './check-location.component.html',
  styleUrls: ['./check-location.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CheckLocationComponent {

  constructor(private locationService: LocationService, private bloodBankService: BloodBankService){
    this.location = {
      id: 0,
      latitude: 0,
      longitude: 0,
      startLatitude: 0,
      startLongitude: 0,
      endLatitude: 0,
      endLongitude: 0,
      delivered: false,
    };
    this.speed = 0;
  }
  map:any; 
  location: DeliveryLocation;
  speed: number;
  bloodBanks: any;
  selectedOriginBank: any;
  selectedDestinationBank: any;
  route: any;
  selectionCompleted: boolean = false;
  
  ngOnInit() {
    this.map = new Map({
      target: 'map',
      layers: [
        new Tile({
          source: new OSM()
        }),
        new VectorLayer({
          source: new VectorSource()
        }),
        new VectorLayer({
          source: new VectorSource()
        }),
        new VectorLayer({
          source: new VectorSource()
        })
      ],
      view: new View({
        center: fromLonLat([19.8268391, 45.2490324]),
        zoom: 15.5
      })
    });

    
    this.loadBloodBanks();

  }
  updateSpeed($event:any){
    this.speed = $event.target.value;
  }
  loadBloodBanks(){
    this.bloodBankService.getAll().subscribe((response:any) =>{
      this.bloodBanks = response.bloodBanks;
      
      this.locationService.getLocation().subscribe((response:DeliveryLocation)=>{
        let delivery = response;
        if(!(delivery)){
          for(let bloodBank of this.bloodBanks){
            this.placeBloodBankMarker(bloodBank);
          }
          return;
        }
        if(Math.abs(delivery.latitude - delivery.endLatitude) < 0.0003 && Math.abs(delivery.longitude - delivery.endLongitude) < 0.0003){
          this.locationService.endDelivery();
          delivery.delivered = true;
          for(let bloodBank of this.bloodBanks){
            this.placeBloodBankMarker(bloodBank);
          }
        }else{
          for(let bloodBank of this.bloodBanks){
            if(!(delivery) || delivery.delivered){
              this.placeBloodBankMarker(bloodBank);
            }else{
              if(delivery.startLatitude == bloodBank.latitude && delivery.startLongitude == bloodBank.longitude){
                this.selectedOriginBank = bloodBank;
                this.placeBloodBankMarkerSelected(bloodBank);
              }else if(delivery.endLatitude == bloodBank.latitude && delivery.endLongitude == bloodBank.longitude){
                this.selectedDestinationBank = bloodBank;
                this.placeBloodBankMarkerSelected(bloodBank);
              }else{
                this.placeBloodBankMarker(bloodBank);
              }
            }
          }
          if(this.selectedDestinationBank && this.selectedOriginBank){
            this.selectionCompleted = true;
            let origin = this.selectedOriginBank.longitude + ',' + this.selectedOriginBank.latitude;
            let destination = this.selectedDestinationBank.longitude + ',' + this.selectedDestinationBank.latitude;
            this.locationService.getRoute(origin, destination).subscribe((response:any)=>{
              this.route = response.features[0].geometry.coordinates;
              
              this.route.forEach((element:any) => {
                const marker = new Feature({
                  geometry: new Point(fromLonLat([element[0], element[1]]))
                });
                marker.setStyle(new Style({
                  image: new Icon({
                    src: 'assets/images/route.png',
                    anchor: [0.5, 1],
                    scale: 0.02
                  })
                }));
                this.map.getLayers().item(2).getSource().addFeature(marker);
              });
            });
            this.updateDeliveryLocation(1);
          } 
        }
        
      });
    });
    
  }

  startDelivery(){
    if(!this.selectionCompleted) return;
    if(!this.speed) return;

    let origin = this.selectedOriginBank.longitude + ',' + this.selectedOriginBank.latitude;
    let destination = this.selectedDestinationBank.longitude + ',' + this.selectedDestinationBank.latitude;
    this.locationService.startDelivery(origin, destination, this.speed).subscribe((response:any)=>{
      this.updateDeliveryLocation(this.speed);
    });

  }

  clearSelection(){
    this.selectedOriginBank = null;
    this.selectedDestinationBank = null;
    this.selectionCompleted = false;
    this.map.getLayers().item(2).getSource().clear();
    this.map.getLayers().item(1).getSource().getFeatures().forEach((feature:any) => {
      feature.setStyle(new Style({
        image: new Icon({
          src: 'assets/images/hospital.png',
          anchor: [0.5, 1],
          scale: 0.075
        })
        }));
    });
  }

  private placeBloodBankMarkerSelected(bloodBank: any){
    const marker = new Feature({
      geometry: new Point(fromLonLat([bloodBank.longitude, bloodBank.latitude]))
    });
    marker.setStyle(new Style({
      image: new Icon({
        src: 'assets/images/hospital_active2.png',
        anchor: [0.5, 1],
        scale: 0.09
      })
    }));
    this.map.getLayers().item(1).getSource().addFeature(marker);


    // Add pointermove interaction to the map
    this.map.on('pointermove', (e:any)=> {
      if(this.selectionCompleted) return;
      var pixel = this.map.getEventPixel(e.originalEvent);
      var hit = this.map.forEachFeatureAtPixel(pixel, (feature:any) => {
        // do something with the feature, for example change its style
        
        feature.setStyle(new Style({
          image: new Icon({
            src: 'assets/images/hospital_active2.png',
            anchor: [0.5, 1],
            scale: 0.09
          })
          }));
        return true;
      });
      if (!hit) {
        // do something when the mouse leaves a feature, for example change the style back
        if(!(this.selectedOriginBank == bloodBank || this.selectedDestinationBank == bloodBank)){
          marker.setStyle(new Style({
            image: new Icon({
              src: 'assets/images/hospital.png',
              anchor: [0.5, 1],
              scale: 0.075,
              
            })
          }));
        }
      }
    }); 

    this.map.on('singleclick', (e:any) => {
      if(this.selectionCompleted) return;
      var feature = this.map.forEachFeatureAtPixel(e.pixel, (feature:any) => {
        return feature;
      });

      if (feature === marker) {
        if(!this.selectedOriginBank) this.selectedOriginBank = bloodBank;
        else {
          this.selectionCompleted = true;
          this.selectedDestinationBank = bloodBank;
          let origin = this.selectedOriginBank.longitude + ',' + this.selectedOriginBank.latitude;
          let destination = this.selectedDestinationBank.longitude + ',' + this.selectedDestinationBank.latitude;
          this.locationService.getRoute(origin, destination).subscribe((response:any)=>{
            this.route = response.features[0].geometry.coordinates;
            
            this.route.forEach((element:any) => {
              const marker = new Feature({
                geometry: new Point(fromLonLat([element[0], element[1]]))
              });
              marker.setStyle(new Style({
                image: new Icon({
                  src: 'assets/images/route.png',
                  anchor: [0.5, 1],
                  scale: 0.02
                })
              }));
              this.map.getLayers().item(2).getSource().addFeature(marker);
            });
          });
        }
        feature.setStyle(new Style({
          image: new Icon({
            src: 'assets/images/hospital_active2.png',
            anchor: [0.5, 1],
            scale: 0.09
          })
          }));
      }
    });
  }


  private placeBloodBankMarker(bloodBank: any){
    const marker = new Feature({
      geometry: new Point(fromLonLat([bloodBank.longitude, bloodBank.latitude]))
    });
    marker.setStyle(new Style({
      image: new Icon({
        src: 'assets/images/hospital.png',
        anchor: [0.5, 1],
        scale: 0.075
      })
    }));
    this.map.getLayers().item(1).getSource().addFeature(marker);


    // Add pointermove interaction to the map
    this.map.on('pointermove', (e:any)=> {
      if(this.selectionCompleted) return;
      var pixel = this.map.getEventPixel(e.originalEvent);
      var hit = this.map.forEachFeatureAtPixel(pixel, (feature:any) => {
        // do something with the feature, for example change its style
        
        feature.setStyle(new Style({
          image: new Icon({
            src: 'assets/images/hospital_active2.png',
            anchor: [0.5, 1],
            scale: 0.09
          })
          }));
        return true;
      });
      if (!hit) {
        // do something when the mouse leaves a feature, for example change the style back
        if(!(this.selectedOriginBank == bloodBank || this.selectedDestinationBank == bloodBank)){
          marker.setStyle(new Style({
            image: new Icon({
              src: 'assets/images/hospital.png',
              anchor: [0.5, 1],
              scale: 0.075,
              
            })
          }));
        }
      }
    }); 

    this.map.on('singleclick', (e:any) => {
      if(this.selectionCompleted) return;
      var feature = this.map.forEachFeatureAtPixel(e.pixel, (feature:any) => {
        return feature;
      });

      if (feature === marker) {
        if(!this.selectedOriginBank) this.selectedOriginBank = bloodBank;
        else {
          this.selectionCompleted = true;
          this.selectedDestinationBank = bloodBank;
          let origin = this.selectedOriginBank.longitude + ',' + this.selectedOriginBank.latitude;
          let destination = this.selectedDestinationBank.longitude + ',' + this.selectedDestinationBank.latitude;
          this.locationService.getRoute(origin, destination).subscribe((response:any)=>{
            this.route = response.features[0].geometry.coordinates;
            
            this.route.forEach((element:any) => {
              const marker = new Feature({
                geometry: new Point(fromLonLat([element[0], element[1]]))
              });
              marker.setStyle(new Style({
                image: new Icon({
                  src: 'assets/images/route.png',
                  anchor: [0.5, 1],
                  scale: 0.02
                })
              }));
              this.map.getLayers().item(2).getSource().addFeature(marker);
            });
          });
        }
        feature.setStyle(new Style({
          image: new Icon({
            src: 'assets/images/hospital_active2.png',
            anchor: [0.5, 1],
            scale: 0.09
          })
          }));
      }
    });
  }
  
  updateDeliveryLocation(speed:number){
    setInterval(() => {
      this.locationService.getLocation().subscribe((result: any) =>{
        this.location = result;
        const marker = new Feature({
          geometry: new Point(fromLonLat([this.location.longitude, this.location.latitude]))
        });
        marker.setStyle(new Style({
          image: new Icon({
            src: 'assets/images/marker.png',
            anchor: [0.5, 1],
            scale: 0.1
          })
        }));
        this.map.getLayers().item(3).getSource().clear();
        this.map.getLayers().item(3).getSource().addFeature(marker);
      });
    }, speed * 1000);
  }
}
