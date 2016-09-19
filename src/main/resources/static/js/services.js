app.service("userService", function () {
    	this.user ={
        		userId:'',
        		userName:'',
        		password:''
        };
    	
    	
    	this.order={
    			orderId:'0',
    			productType:'',
    			isStandalone:'',
    			amount:'',
    			quantity:'',	
    			counterParty:'',
    			costPrice:'',
    			fillAmount:'0'
    			
    	};
    	
    	this.requestinfo={
    			user: this.user,
    			order:this.order
    	};
    	
    	
    	
        
    });