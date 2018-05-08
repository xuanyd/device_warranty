import { Component, OnInit,Output, 
  ViewEncapsulation,ViewChild,ElementRef } from '@angular/core';
import { HttpService } from '../../common/util/http.service';
import { Router } from '@angular/router';
import { ConfirmComponent } from '../../common/lib/confirm/confirm.component';
import { ModalComponent } from '../../common/lib/modal/modal.component';
import { GridComponent } from '../../common/lib/grid/grid.component';

@Component({
  selector: 'c-search-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss'],
  encapsulation: ViewEncapsulation.None
})

export class PatientComponent implements OnInit {

  @ViewChild('patientGrid')
  patientGrid: GridComponent; 
  
  @ViewChild('confirm')
  confirm: ConfirmComponent;
  
  @ViewChild('modal')
  modal: ModalComponent;  

  url: String = "admin/search/patientlist";
  patientGridHeaders: any;

  searchParams: any  = {
    name: ''
  };

  constructor(private router: Router,private httpService: HttpService) {
   this.patientGridHeaders = [
      {'key':'id', 'name': '编号','keyType': true, 'width': 'col-sm-1'},
      {'key':'patient_name', 'name': '患者姓名', 'width': 'col-sm-2'},
      {'key':'sex', 'name': '性别', 'deleteTitle': true, 'width': 'col-sm-3'},
      {'key':'visit_department', 'name': '门诊科室', 'width': 'col-sm-1'},
      {'key':'study_item', 'name': '检查项目', 'width': 'col-sm-1'},
      {'key':'report_date_time', 'name': '检查时间', 'width': 'col-sm-1'}
    ];

  }

  ngOnInit() {
  }

  toCheckList() {
    let selectInfo = this.patientGrid.getSelect();
    if(!selectInfo.isSelect)
      return;
    this.router.navigate(['/app/search/check-list',selectInfo.id]);
  }

  getPatientList() {
    this.patientGrid.query();
  }
  
}
