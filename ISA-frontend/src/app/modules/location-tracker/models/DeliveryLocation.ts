export interface DeliveryLocation{
  id: number,
  latitude: number,
  longitude: number
  startLatitude: number,
  startLongitude: number
  endLatitude: number,
  endLongitude: number,
  delivered: boolean
}