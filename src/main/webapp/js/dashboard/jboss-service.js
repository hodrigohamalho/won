app.factory('jbossService', function($resource) {
	return $resource('rest/jboss/:id', { id:'@id' }, { 
		update: { method: 'PUT' },
		serversByGroup: { method: 'GET', params: { dc: '1', by: 'group'}, url: 'rest/jboss/servers' }
	});
});
