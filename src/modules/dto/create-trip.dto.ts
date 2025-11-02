import { IsDecimal, IsNumber, IsOptional, IsString } from "class-validator";

export class CreateTripDto {
  @IsNumber()
  rider_id: number;

  @IsNumber()
  @IsOptional()
  driver_id: number;

  @IsString()
  pickup_zone: string;

  @IsString()
  drop_zone: string;

  @IsDecimal({ decimal_digits: "0,2" })
  distance_km: string;

  @IsDecimal({ decimal_digits: "0,2" })
  base_fare: string;

  @IsDecimal({ decimal_digits: "0,2" })
  surge_multiplier: string;
}
