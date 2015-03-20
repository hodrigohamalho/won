app.factory('jbossService', function($resource) {
	return $resource('rest/jboss/:id', { id:'@id' }, { 
		update: { method: 'PUT' },
		resume: { method: 'GET', url: 'rest/jboss/resume' }
	});
});
