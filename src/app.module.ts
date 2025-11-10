import { Module } from "@nestjs/common";
import { AppController } from "./app.controller";
import { AppService } from "./app.service";
import { TripModule } from "./modules/trip.module";
import { DatabaseModule } from "./database/database.module";
import { PrometheusModule } from "@willsoto/nestjs-prometheus";

@Module({
  imports: [DatabaseModule, TripModule, PrometheusModule.register()],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
