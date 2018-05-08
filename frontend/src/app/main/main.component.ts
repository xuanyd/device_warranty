import { Component, OnInit,ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorage } from '../common/storage/local.storage';


@Component({
  selector: 'c-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})

export class MainComponent implements OnInit {

	leftMenuClosed : boolean = false;
  	url:string='1';
	constructor(private ls: LocalStorage){
	}
	
	ngOnInit() {

	}

	changeMenuStatus() {
		this.leftMenuClosed = !this.leftMenuClosed;
	}

}
