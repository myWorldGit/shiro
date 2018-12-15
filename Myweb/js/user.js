$(function(){
	axios.defaults.baseURL = 'http://47.93.238.67/lanpangzi';
	var topsinfo = function(content,type){
		var topsbox = document.getElementById('tops-info');
		var child = topsbox.firstElementChild;
		child.innerHTML=content;
		if(type =='success'){
			topsbox.style.backgroundColor='rgba(92,181,94,.93)';
		}else if(type=='warning'){
			topsbox.style.backgroundColor='rgba(221,111,50,.93)';
		}
		else if(type=='error'){
			topsbox.style.backgroundColor='rgba(232, 158, 182,.93)';
		}
		$(topsbox).fadeIn(400);
		$(topsbox).fadeOut(4000);
	}

	var modal = {
		template:'#modal-modify',
		data:function(){
			return{
				role:-1
			}
		},
		watch:{
		},
		methods:{
			close:function(){
				this.$emit('close-box');
				document.getElementById('mofidy-btn').disabled=true;

			},
			modify:function(id){
				var params = new URLSearchParams();
				params.append("id",id);
				params.append("roleId",this.role);
				axios.post('/admin/user/update',params).then((response) => {
					//触发提示框
					topsinfo('success','success');
					this.$emit('close-box');
					document.getElementById('mofidy-btn').disabled=true;
					this.params.user.roleId= this.role;
			}).catch(function (error) {
					console.log(error);
					topsinfo(error,'error');
				});
			},
			roleChange:function (e) {
				var val = e.target.value;
				this.role= val;
				if(val!=this.params.user.roleId){
					document.getElementById('mofidy-btn').disabled=false;
					e.target.style.borderColor='#08cc55';
					e.target.style.color='#08cc55';
				}else {
					document.getElementById('mofidy-btn').disabled=true;
					e.target.style.borderColor='#66afe9';
					e.target.style.color='#555';
				}
			}
		},
		props:['params']
		
	}

	new Vue({
		el:'#application',
		methods:{
			modify:function(uid){
				this.subProp.isshow=true;
				this.subProp.user=this.users[uid];
				this.currentRole=this.subProp.user.roleId;
			},
			closebox:function(){
				this.subProp.isshow=false;
			},
			formsubmit:function(){
				alert(this.keyword);
				this.keyword='';
			},
			logout:function(){
				alert('logout')
			},
			priorBtn:function(){
				if(this.currentPage==1){
					return;
				}
				--this.currentPage;
			},
			nextBtn:function(){
				if(this.currentPage==this.total){  //最大页面
					return;
				}
				++this.currentPage;
			},
			numberBtn:function (p) {
				if(this.currentPage==p){
					return;
				}
				this.currentPage=p;
			},
			firstBtn:function(){
				if(this.currentPage==1){
					return;
				}
				this.currentPage=1;
				this.rangMin=1;
				this.rangMax=5;
			},
			maxBtn:function () {
				if(this.currentPage==this.total){
					return;
				}
				this.currentPage=this.total;
				this.rangMin=this.total-5;
				this.rangMax=this.total;
			},
			toPageBtn:function () {
				if(this.toPage=="") return;
				if(this.toPage>this.total){
					$(".toPage-input").addClass("redBorder")
					return;
				}
				$(".toPage-input").removeClass("redBorder")

				//做跳转


				this.toPage='';
			}


		},
		data:{
			users:[],
			subProp:{isshow:false,user:{}},
			keyword:'',
			currentPage:1,
			rangMax:5,
			rangMin:1,
			total:0,
			toPage:''
		},
		watch:{
			toPage:function(){
				this.toPage
				var reg =/^[1-9]\d*$/;
				if(!reg.test(this.toPage)){
					this.toPage=this.toPage.substr(0,this.toPage.length-1);
				}
			},
			currentPage:function(){
				if(this.currentPage> this.rangMax){
					this.rangMax++;
					this.rangMin++;
				}
				if(this.currentPage< this.rangMin){
					this.rangMin--;
					this.rangMax--;
				}
				$(".numberbtnflag").removeClass("bypage-active")
				$(".numberbtnflag").eq(this.currentPage-1).addClass("bypage-active");
			}
		},
		components:{
			'modal-modify':modal,
		},
		filters:{
			timeform:function (time) {
				if(time=='')  return '未知时间';
				var date = new Date(time);
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+' '
					+date.getHours()+":"+date.getMinutes()+':'+date.getSeconds();
			}
		},
		created:function(){
		},
		mounted:function(){
			var params = new URLSearchParams();
			params.append("currPage","1");
			params.append("pageSize","10");
			axios.post('/admin/user/findAll',params).then((response) => {
				this.users=response.data.users;
				this.total=16//Math.ceil(response.data.count/10);
			}).catch(function (error) {
			    console.log(error);
			});
			
		}
	})
})