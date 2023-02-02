function initBrowserFeatures() {
	var cname = 'browserFeatures';
	var cur = getCookie(cname);
	if (cur == null) {
		var features = 0;
		features += _testInputFeature('number') ? 1 : 0;
		features += _testInputFeature('range') ? 2 : 0;

		features += _testInputFeature('date') ? 4 : 0;
		features += _testInputFeature('time') ? 8 : 0;
		features += _testInputFeature('datetime-local') ? 16 : 0;

		features += _testInputFeature('month') ? 32 : 0;
		features += _testInputFeature('week') ? 64 : 0;

		setCookie(cname, features, 3600, '/');
	}
	return cur;
}

function _testInputFeature(type) {
	var input = document.createElement('input');
	var invalidValue = 'test';
	input.setAttribute('type', type);
	input.setAttribute('value', invalidValue);
	return (input.value !== invalidValue);
}

function getCookie(cname) {
	var v = document.cookie.match('(^|;) ?' + cname + '=([^;]*)(;|$)');
	return v ? v[2] : null;
}

function setCookie(cname, cvalue, exdays, path) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=" + path;
}

function ajax(method, url, queryParams, headerFn, jsonResult, onSuccess, onError, onDone) {
	var xhr = new XMLHttpRequest();
	xhr.open(method, url, true);

	if (headerFn == null) {
		if (method == 'POST') xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	} else headerFn(xhr);

	xhr.onreadystatechange = function() {
		if (this.readyState === XMLHttpRequest.DONE) {
			var res = jsonResult ? JSON.parse(xhr.responseText) : xhr.responseText;

			if (this.status === 200) {
				if (onSuccess != null)
					onSuccess(res);
			} else {
				if (onError != null)
					onError(res, this.status);
			}
			if (onDone != null)
				onDone(res, this.status);
		}
	}
	xhr.send(queryParams);
}