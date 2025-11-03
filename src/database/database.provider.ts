import { Sequelize } from "sequelize-typescript";
import { Driver } from "src/models/driver.model";
import { Payment } from "src/models/payment.model";
import { Rating } from "src/models/rating.model";
import { Rider } from "src/models/rider.model";
import { Trip } from "src/models/trip.model";

export const databaseProviders = [
  {
    provide: "SEQUELIZE",
    useFactory: async () => {
      const sequelize = new Sequelize({
        dialect: "mysql",
        host: "mysql",
        port: 3306,
        username: "root",
        password: "root",
        database: "local_db",
      });
      sequelize.addModels([Rider, Driver, Payment, Trip, Rating]);
      await sequelize.sync();
      return sequelize;
    },
  },
];
