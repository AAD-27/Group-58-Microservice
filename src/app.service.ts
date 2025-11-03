import { Injectable } from "@nestjs/common";
import axios from "axios";

@Injectable()
export class AppService {
  async getHello(): Promise<any> {
    const data = await axios.get("http://nestjs-app-2:3001/");
    return data;
  }
}
