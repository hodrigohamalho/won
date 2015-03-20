app.factory('dcService', function($resource) {
	return $resource('rest/dc/:id', { id:'@id' }, { 
		update:{method:'PUT'},
		testConnection: {method: 'GET', url: '/rest/dc/test-connection/:id'}
	});
});
