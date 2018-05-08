import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';  
import { HttpService } from '../../util/http.service';

@Component({  
  selector: 'app-confirm',  
  templateUrl: './confirm.component.html',  
  styleUrls: ['./confirm.component.css']  
})  
export class ConfirmComponent implements OnInit {  

	title: String = '确认';
	content: String = '确认';
	showStyle: String = 'none';
	isConfirm: boolean = false;

  callback: any = null;

  constructor() { }  

  ngOnInit() {  
  }

  show(title, content, callback) {
  	this.title = title;
  	this.content = content;
  	this.showStyle = 'block';
    this.callback = callback;
  }

  confirm() {
    this.callback();
  	this.close(true);
  }

  cancel() {
  	this.close(false);
  }

  close(isConfirm) {
  	this.isConfirm = isConfirm;
  	this.showStyle = 'none';
  }

}  