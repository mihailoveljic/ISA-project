import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: 'transformBloodType',
})
export class TransformBloodTypePipe implements PipeTransform {
  transform(value: any, ...args: any[]) {
    switch(value){
      case "AB_POS": 
        return "AB+";
      case "A_POS":
        return "A+";
      case "B_POS":
        return "B+";
      case "ZERO_POS":
        return "0+";
      case "AB_NEG":
        return "AB-";
      case "A_NEG":
        return "A-";
      case "B_NEG":
        return "B-";
      case "ZERO_NEG":
        return "0-";
      default:
        return "UNKNOWN";
    }
  }
}
