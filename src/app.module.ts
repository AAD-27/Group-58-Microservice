import { Module } from "@nestjs/common";
import { AppController } from "./app.controller";
import { AppService } from "./app.service";
import { TripModule } from "./modules/trip.module";
import { DatabaseModule } from "./database/database.module";

@Module({
  imports: [DatabaseModule, TripModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
