import { WorkTime } from './WorkTime';
export interface Center{
  id: number;
  name: string;
  description: string;
  averageRating: number;
  startTime: string;
  endTime: string;
  street: string;
  number: string;
  city: string;
  country: string;
}