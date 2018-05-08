import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';  
import { HttpService } from '../../util/http.service';

@Component({  
  selector: 'app-grid',  
  templateUrl: './grid.component.html',  
  styleUrls: ['./grid.component.css']  
})  
export class GridComponent implements OnInit {  

  @Input() 
  url: string;
  @Input() 
  headers: any;
  @Input()
  params: any;  

  dataUrl: string[];  
  selectInfo: any = {
    id: -1,
    name: null,
    isSelect: false
  };
  pagenation: any = {
    'currentPage': 1,
    'totalPage': 10,
    'pageSize': 10,
    'totalItems': 10
  };
  pageArray: number[] = [];
  dataList: String = "";
  isLoading: boolean = false;
  constructor(private httpService: HttpService) { }  

  ngOnInit() {  
  }

  select(id, title) { 
    this.selectInfo.id = id;
    this.selectInfo.name = title;
    this.selectInfo.isSelect = true;
  }

  getSelect(){
    return this.selectInfo;
  }

  prePage() {
    if(this.pagenation.currentPage > 1) {
      this.pagenation.currentPage = this.pagenation.currentPage -1;
      this.toQuery();
    }
  }

  nextPage() {
    if(this.pagenation.currentPage < this.pagenation.totalPage){
      this.pagenation.currentPage = this.pagenation.currentPage + 1;
      this.toQuery();
    }
  }

  changeCurrentPage(page){
    if(this.pagenation.currentPage != page) {
      this.pagenation.currentPage = page;
      this.toQuery();
    }
  }
  change(){
      
  }
  query() {
    this.pagenation.currentPage = 1;
    this.toQuery();
  }
  toQuery() {
    this.isLoading = true;  
    let that = this;
    let page = this.pagenation.currentPage;
    this.params.page = this.pagenation.currentPage;
    this.httpService.request({
      method: "POST",
      url: that.url,
      data: this.params
    }).then(result => {
      this.isLoading = false; 
      this.pageArray = [];
      if(result.data.flag == "1000"){
        that.dataList = result.data.pageInfo.infoList;
        that.pagenation.totalItems = result.data.pageInfo.totalCount;
        that.pagenation.totalPage = result.data.pageInfo.totalPage;
        that.initShowPageList(that.pagenation.totalPage);
      }
    });
  }

  initShowPageList(totalPage){
    console.log(totalPage);
    if (totalPage < 10){
      this.pageArray = [];
      for(let i = 0; i < totalPage; i++ ){
        this.pageArray[i] = i+1;
      }
      console.log(this.pageArray); 
      return;
    }
    if(this.pagenation.currentPage < 8 ) {
      this.pageArray = [];
      for(let i = 0; i < 10; i++ ){
        this.pageArray[i] = i+1;
      } 
    } else {
      this.pageArray = [];
      for(let i = this.pagenation.currentPage - 5, j = 0; i <= this.pagenation.currentPage+4; i ++,j++) {
        if(i <= this.pagenation.totalPage) {
          this.pageArray[j] = i;
        }
      }
    }
  }

  timestampToTime(timestamp) {
    let date = new Date(timestamp);
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    let D = date.getDate() + ' ';
    let h = date.getHours() + ':';
    let m = date.getMinutes() + ':';
    let s = date.getSeconds();
    return Y+M+D+h+m+s;
  }  
}  