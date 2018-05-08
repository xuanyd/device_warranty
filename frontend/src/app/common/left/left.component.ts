import { Component, OnInit,ElementRef  } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorage } from '../storage/local.storage';

@Component({
  selector: 'c-left-menu',
  templateUrl: './left.component.html',
  styleUrls: ['./left.component.scss']
})

export class LeftComponent implements OnInit {
	
  leftMenus: Array<boolean> = [false, false, false, false, false,false,false,false,false,false,false,false,false];
  userName: string='';
  _ls:LocalStorage;
  leftMenuJson = [
    {"id": 1, name: "主页",pid: 0, url: "/app/home", cls: "fa-home"},
    {"id": 2, name: "内容设置",pid: 0, url: "", cls: "fa-book",  
      childs: [
        {"id": 3, name: "文章管理",pid: 2, url: "/app/sitecfg/notice"},
        {"id": 4, name: "友情链接",pid: 2, url: "/app/sitecfg/link"},
        {"id": 5, name: "栏目管理",pid: 2, url: "/app/sitecfg/column"},
        {"id": 11, name: "课件管理",pid: 2, url: "/app/sitecfg/courseware"},
        {"id": 12, name: "视频管理",pid: 2, url: "/app/sitecfg/vedio"}
      ]
    },
    {"id": 8, name: "心电图管理",pid: 0, url: "", cls: "fa-heartbeat",
      childs:[
        {"id": 9, name: "心电图信息",pid: 8, url: "/app/search/patient"},
        {"id": 10, name: "诊断模板",pid: 8, url: "/app/search/check-template"}
      ]
    },
    {"id": 6, name: "用户管理",pid: 0, url: "", cls: "fa-users",
      childs:[
        {"id": 7, name: "密码修改",pid: 6, url: "/app/user/change-pwd"}
      ]
    }
  ];

  hideDisplay: boolean = false;

  constructor(private router: Router,
    private ls: LocalStorage) {
    this.userName = ls.getObject('userName');
    this._ls = ls;
  }
   
  ngOnInit() {
  }
  openLeft(idx,url) {
    for(let i =0; i < this.leftMenus.length; i++){
      if(i == idx) {
        this.leftMenus[idx] = !this.leftMenus[idx];
      }
      else {
        if(url == "")
          this.leftMenus[i] = false;
      }
    }
    if(url != "") {
      this.router.navigate([url]);
      this._ls.setObject("curl",url);
    }
  }

  openHideMenu() {
    this.hideDisplay = !this.hideDisplay;
  }
}

