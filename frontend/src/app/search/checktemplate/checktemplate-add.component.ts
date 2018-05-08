import { Component, OnInit,AfterContentInit,OnDestroy,ViewChild } from '@angular/core'
import { Validators, FormControl, FormGroup, FormBuilder,FormsModule,ReactiveFormsModule } from '@angular/forms'
import { HttpService } from '../../common/util/http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'c-sitecfg-checktemplate-add',
  templateUrl: './checktemplate-add.component.html',
  styleUrls: ['./checktemplate.component.scss']
})

export class CheckTemplateAddComponent implements OnInit {
  
  templateAddForm: FormGroup;

  constructor(private router: Router, private httpService: HttpService,
      private formBuilder: FormBuilder) {
      let templateNameFc = new FormControl('', Validators.compose([Validators.required]));
      let contentFc = new FormControl('', Validators.compose([Validators.required]));
      let remarkFc = new FormControl('', Validators.compose([Validators.required]));
      this.templateAddForm = this.formBuilder.group({
        template_name: templateNameFc,
        content: contentFc,
        remark: remarkFc
      });
    }

	/**
  * 初始化
  */
  ngOnInit() {
    let that = this
  }

  ngOnDestroy(): void {
  }

  add() {
    if (!this.templateAddForm.valid)
      return;
    let that = this;
    this.httpService.request({
      method: "POST",
      url: "admin/search/checktemplate-add",
      data:{
        template_name: that.templateAddForm.value.template_name,
        content: that.templateAddForm.value.content,
        remark: that.templateAddForm.value.remark
      }
    }).then(result => {
      if(result.data.flag == "1000"){
        alert("保存成功");
      }
    });
  }

  back() {
   this.router.navigate(['/app/search/check-template']);
  }

}
