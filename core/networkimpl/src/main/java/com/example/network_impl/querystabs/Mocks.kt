package com.example.network_impl.querystabs

fun Mocker.configure() = mockerConfig {
	testStringQuery2()
	disable {}
}

fun MockConfig.testStringQuery2() {
	mock {
		predicate { request ->
			request.url.toString() == "http://www.gogasoft.gog/myData"
		}
		creator {
			MockData(
				body = asset("StabsForRest.json")
			)
		}
	}
}
