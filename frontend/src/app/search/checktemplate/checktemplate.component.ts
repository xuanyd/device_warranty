import { Component, OnInit,Output, 
  ViewEncapsulation,ViewChild,ElementRef } from '@angular/core';
import { HttpService } from '../../common/util/http.service';
import { Router } from '@angular/router';
import { ConfirmComponent } from '../../common/lib/confirm/confirm.component';
import { ModalComponent } from '../../common/lib/modal/modal.component';
import { GridComponent } from '../../common/lib/grid/grid.component';

@Component({
  selector: 'c-search-check-template',
  templateUrl: './checktemplate.component.html',
  styleUrls: ['./checktemplate.component.scss'],
  encapsulation: ViewEncapsulation.None
})

export class CheckTemplateComponent implements OnInit {

  @ViewChild('templateGrid')
  templateGrid: GridComponent; 
  
  @ViewChild('confirm')
  confirm: ConfirmComponent;
  
  @ViewChild('modal')
  modal: ModalComponent;  

  url: String = "admin/search/checktemplate-list";
  templateGridHeaders: any;

  searchParams: any  = {
    name: ''
  };

  constructor(private router: Router,private httpService: HttpService) {
   this.templateGridHeaders = [
      {'key':'id', 'name': '编号','keyType': true, 'width': 'col-sm-1'},
      {'key':'template_name', 'name': '模板名', 'width': 'col-sm-2'},
      {'key':'content', 'name': '模板名', 'deleteTitle': true, 'width': 'col-sm-3'},
      {'key':'remark', 'name': '备注', 'width': 'col-sm-1'}
    ];

  }

  ngOnInit() {
  }

  getCheckTemplateList() {
    this.templateGrid.query();
  }

  toAdd(){
   this.router.navigate(['/app/search/check-template-add']);
  }

  toDelete() {
    let selectInfo = this.templateGrid.getSelect();
    if(!selectInfo.isSelect)
      return;
    let that = this;
    that.confirm.show('确认删除', '确认删除"'+selectInfo.name+'"吗?', function(){
      that.httpService.request({
        method: "POST",
        url: "admin/search/checktemplate-delete",
        data: {
          id: selectInfo.id
        }
      }).then(result => {
        if(result.data.flag == "1000"){
          that.templateGrid.query();
          that.modal.show('删除提示','删除记录成功');
        }
      });
    });
  }
}
