app.factory('jbossService', function($resource) {
	return $resource('rest/jboss/:id', { id:'@id' }, { 
		update: { method: 'PUT' },
		serversByGroup: { method: 'GET', params: { dc: '1', by: 'group'}, url: 'rest/jboss/servers' },
		deploy: { method: 'GET', params: { dc: '1', host: '', server: '' }, url: 'rest/jboss/deploy' }
	});
});
