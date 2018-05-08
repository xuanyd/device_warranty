import { Component, OnInit,ViewEncapsulation,ViewChild,ElementRef } from '@angular/core';
import { HttpService } from '../../common/util/http.service';
import { Router, ActivatedRoute } from '@angular/router';
import { GridComponent } from '../../common/lib/grid/grid.component';
import { ConfirmComponent } from '../../common/lib/confirm/confirm.component';
import { ModalComponent } from '../../common/lib/modal/modal.component';

@Component({
  selector: 'c-sitecfg-check',
  templateUrl: './check.component.html',
  styleUrls: ['./check.component.scss'],
  encapsulation: ViewEncapsulation.None
})

export class CheckComponent implements OnInit {

  @ViewChild('checkGrid')
  checkGrid: GridComponent; 
  
  @ViewChild('confirm')
  confirm: ConfirmComponent; 

  @ViewChild('modal')
  modal: ModalComponent;  

  url: String = "admin/search/check-list";
  checkGridHeaders: any;
  
  searchParams: any;
	
  constructor(private router: Router,
    private acRouter: ActivatedRoute,
    private httpService: HttpService,private el: ElementRef) {
    this.checkGridHeaders = [
      {'key':'id', 'name': '编号','keyType': true, 'width': 'col-sm-1'},
      {'key':'patient_name', 'name': '患者姓名', 'deleteTitle': true, 'width': 'col-sm-2'},
      {'key':'check_info', 'name': '诊断信息',  'width': 'col-sm-3'},
      {'key':'check_date', 'name': '诊断时间',  'width': 'col-sm-3'}
    ];

    this.searchParams = {
        id: this.acRouter.params['value']['id']
    };
	}

  ngOnInit() {
  }
  
  getCheckList() {
    this.checkGrid.query();
  }

  toEdit() {
    this.router.navigate(['/app/search/check-edit'])
  }

  toAdd(){
    this.router.navigate(['/app/search/check-add',this.searchParams.id])
  }
  toDelete() {
    let selectInfo = this.checkGrid.getSelect();
    if(!selectInfo.isSelect)
      return;
    let that = this;
    that.confirm.show('确认删除', '确认删除"'+selectInfo.name+'"吗?', function(){
      that.httpService.request({
        method: "POST",
        url: "admin/search/check-delete",
        data: {
          id: selectInfo.id
        }
      }).then(result => {
        if(result.data.flag == "1000"){
          that.checkGrid.query();
          that.modal.show('删除提示','删除记录成功');
        }
      });
    });
  }
}
