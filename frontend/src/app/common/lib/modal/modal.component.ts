import {Component, Input} from "@angular/core";


@Component({
  selector: 'app-modal',
  templateUrl: "./modal.component.html"
})
export class ModalComponent {

  showStyle: String = 'none';
  isShow: boolean = false;
  title: String;
  content: String;

  show(title, content) {
  	this.title = title;
  	this.content = content;
  	this.showStyle = 'block';
  	this.isShow = true;	
  }

  close() {
  	console.log('close...');
  	this.isShow = false;
  	this.showStyle = 'none';
  }
}