export class Message {
  id?: number = undefined;
  uId?: number = undefined;
  message: string;
  name: string;
  dateCreated: string;
  constructor() {
    this.message = '';
    this.name = '';
    this.dateCreated = '';
  }
}
