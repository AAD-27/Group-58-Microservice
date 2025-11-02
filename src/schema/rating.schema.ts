import { TripSchema } from "./trip.schema";

export class RatingSchema {
  id: number;
  tripId: number;
  riderRating?: number;
  driverRating?: number;
  comment?: string;
  trip: TripSchema;
}
