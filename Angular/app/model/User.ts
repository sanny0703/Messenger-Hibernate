export class User{
    uId? : number = undefined
    name:string
    email:string
    password:string
    isAdmin:string
    constructor(){
        this.name=''
        this.email=''
        this.password=''
        this.isAdmin =''
    }

}