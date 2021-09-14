export class Comment{
    id?:number= undefined
    cId?:number=undefined
    uId?:number=undefined
    comment:string
    author:string
    dateCreated:string
    constructor(){
        this.comment =''
        this.author=''
        this.dateCreated=''
    }

}