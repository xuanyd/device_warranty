import { Component, OnInit,AfterContentInit,OnDestroy,ViewChild } from '@angular/core'
import { Validators, FormControl, FormGroup, FormBuilder,FormsModule,ReactiveFormsModule } from '@angular/forms'
import { HttpService } from '../../common/util/http.service'
import { Router, ActivatedRoute } from '@angular/router'

@Component({
  selector: 'c-sitecfg-check',
  templateUrl: './check-edit.component.html',
  styleUrls: ['./check.component.scss']
})

export class CheckEditComponent implements OnInit {
  
  checkEditForm: FormGroup;
  editDisabled:string = '';
  patientId: String;
  templateList: any;
  checkInfo: any = {
    patientId: this.acRouter.params['value']['id'],
    templateId:0,
    content:'',
    remark: ''
  }
  constructor(private router: Router, 
    private acRouter: ActivatedRoute,
    private httpService: HttpService,
      private formBuilder: FormBuilder) {
      let contentFc = new FormControl('', Validators.compose([Validators.required,Validators.minLength(5), Validators.maxLength(300)]))
      let remarkFc = new FormControl('', Validators.compose([Validators.required,Validators.minLength(5), Validators.maxLength(300)]))
      let templateIdFc = new FormControl();
      this.checkEditForm = this.formBuilder.group({
        content: contentFc,
        remark: remarkFc,
        templateId: templateIdFc
      });
      this.patientId = this.acRouter.params['value']['id'];
      this.getTemplateList();
    }

	/**
  * 初始化
  */
  ngOnInit() {
  }

  ngOnDestroy(): void {
  }

  edit() {
    if (!this.checkEditForm.valid)
      return;
    let that = this;
    this.httpService.request({
      method: "POST",
      url: "admin/search/check-edit",
      data:that.checkInfo
    }).then(result => {
      if(result.data.flag == "1000"){
        alert("保存成功");
      }
    });
  }

  getTemplateList(){
    let that = this;
    this.httpService.request({
      method: "POST",
      url: "admin/search/checktemplate-list",
      data: {
        page: 1,
        pageSize: 100
      }
    }).then(result => {
      if(result.data.flag == "1000"){
        that.templateList = result.data.pageInfo.infoList;
      }
    });
  }

  changeTemplate(e) {
    for (var i = 0; i < this.templateList.length; i++) {
      if(this.checkInfo.templateId == this.templateList[i].id)
        this.checkInfo.content = this.templateList[i].content;
    }
  }

  back() {
    this.router.navigate(['/app/search/check-list',this.patientId]);
  }

}
