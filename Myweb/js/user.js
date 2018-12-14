$(function(){
	var modal = {
		template:'#modal-modify',
		data:function(){
			return{

			}
		},
		methods:{
			close:function(){
				this.$emit('close-box');
			},
			modify:function(){

			}
		},
		props:['params']
		
	}
	

	axios.defaults.baseURL = 'http://47.93.238.67/lanpangzi';
	

	new Vue({
		el:'#application',
		methods:{
			modify:function(uid){
				this.subProp.isshow=true;
				this.subProp.user=this.users[uid];
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
			}
		},
		data:{
			users:[],
			subProp:{isshow:false,user:{}},
			keyword:''
		},
		watch:{

		},
		components:{
			'modal-modify':modal
		},
		created:function(){
		},
		mounted:function(){

			axios.post('/admin/user/findAll')
			.then((response) => {
				var userList = response.data;
				this.users=userList;
			})
			.catch(function (error) {
			    console.log(error);
			});
			
		}
	})
})