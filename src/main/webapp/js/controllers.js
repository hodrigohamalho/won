app.controller('MainCtrl', function($scope, $timeout, jbossService) {
	this.projectBoldName = 'CLI ';
	this.projectName = 'Crawler';
	this.userName = 'Rodrigo Ramalho';
	this.headerText = 'JBoss Monitor Project';
	this.descriptionText = 'This project is a kind of JBoss CLI Crawler';

	$scope.resume = function(){
		jbossService.serversByGroup(function(data){
			$scope.serversParent = data;
		});
	};

	$scope.deploy = function(server, group){
		jbossService.deploy({dc: 1, host: server.host, server: server.name}, function(response){
			server.deploys = response;
		});
	};
	
	$scope.sumGroup = function(name, deploy){
		if (deploy.subsystem && deploy.subsystem.web){
			var activeSession = deploy.subsystem.web["active-sessions"];
			var deployContext = deploy.subsystem.web["context-root"];
			
			if (activeSession && deployContext){
				if (!$scope.serversParent[name]["deploys"]){
					$scope.serversParent[name]["deploys"] = {};
					$scope.serversParent[name]["deploys"][deployContext] = {};
				}
				if (!$scope.serversParent[name]["deploys"][deployContext]){
					$scope.serversParent[name]["deploys"][deployContext] = {};
				}
				
				if ($scope.serversParent[name]["deploys"][deployContext]["active-sessions"]){ 
					$scope.serversParent[name]["deploys"][deployContext]["active-sessions"] += activeSession;
				}else{
					$scope.serversParent[name]["deploys"][deployContext] = { "active-sessions":  activeSession}
				}
			}
		}
	};

	$scope.resume();
});

app.controller('DomainCtrl', function($scope, dcService) {

	$scope.formCtrl = false;

	$scope.showForm = function(){
		$scope.formCtrl = true
	};

	$scope.hideForm = function(){
		$scope.formCtrl = false;
	}

	$scope.reset = function(){
		$scope.dc = {id: null, host:'', port:'', username: '', password:'', active: false};
		$scope.hideForm();
	};

	var scrollToElement = function (elementId) {
		$('html, body').animate({
			scrollTop: $(elementId).offset().top
		}, 2000)
	};

	$scope.list = function(){
		$scope.dcs = dcService.query();
	}

	$scope.save = function(){
		if($scope.dc.id != null){
			dcService.update($scope.dc, function(){
				$scope.reset();
				$scope.list();
			});
		} else {
			dcService.save($scope.dc, function(){
				$scope.reset();
				$scope.list();
			});
		}
	};

	$scope.edit = function(dc){
		$scope.dc = dc;
		scrollToElement("#form-dc");
		$scope.showForm();
	};

	$scope.remove = function(dc){
		var confirm = window.confirm('Tem certeza que deseja excluir o produto '+ dc.host+ '?');
		if(confirm){
			dcService.delete({}, {'id': dc.id}, function(){
				$scope.list();
			});
		}
	};

	$scope.testConnection = function(id){
		dcService.testConnection(id, function(isConnected){
			if (isConnected){
				console.log("Conectado!");
			}else{
				console.log("Não conectado!");
			}
		});
	};

	$scope.list();
});
