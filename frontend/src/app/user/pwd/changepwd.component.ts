import { Component, OnInit,ViewEncapsulation,
  ViewChild,ElementRef } from '@angular/core';
import { Validators, FormControl, FormGroup, 
  FormBuilder,FormsModule,ReactiveFormsModule } from '@angular/forms'

import { HttpService } from '../../common/util/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'c-user-changepwd',
  templateUrl: './changepwd.component.html',
  styleUrls: ['./changepwd.component.scss'],
  encapsulation: ViewEncapsulation.None
})

export class ChangePwdComponent implements OnInit {

  changePwdForm: FormGroup;

  constructor(private router: Router,private httpService: HttpService,
    private formBuilder: FormBuilder) {
    let oldpwdFc = new FormControl('', 
      Validators.compose([Validators.required,Validators.minLength(5), Validators.maxLength(15)]));
    let newpwdFc = new FormControl('', 
      Validators.compose([Validators.required,Validators.minLength(5), Validators.maxLength(15)]));
    let newpwd2Fc = new FormControl('', 
      Validators.compose([Validators.required,Validators.minLength(5), Validators.maxLength(15)]));
    this.changePwdForm = this.formBuilder.group({
      oldpwd: oldpwdFc,
      newpwd: newpwdFc,
      newpwd2: newpwd2Fc
    });
	}
  ngOnInit() {
  }

  back() {
    this.router.navigate(['/app/home']);
  }
  
}
