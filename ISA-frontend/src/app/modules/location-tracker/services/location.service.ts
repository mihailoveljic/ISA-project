import { DeliveryLocation } from './../models/DeliveryLocation';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  

  constructor(private http: HttpClient) { }


  getLocation(): Observable<DeliveryLocation>{
    return this.http.get<DeliveryLocation>(environment.bloodBank + "/api/delivery-location");
  }

  getRoute(origin:string, destination:string){
    return this.http.get("https://api.openrouteservice.org/v2/directions/driving-car?api_key=5b3ce3597851110001cf62484d76a7fe227e4b79afb08791a16cb322&start=" + origin + "&end=" + destination);
  }

  startDelivery(origin: string, destination: string, speed: number) {
    return this.http.get<DeliveryLocation>(environment.bloodBank + "/api/delivery-location/begin?origin="+origin+"&destination=" + destination+"&speed=" + speed);
  }
  endDelivery() {
    return this.http.get(environment.bloodBank + "/api/delivery-location/end");
  }
}
