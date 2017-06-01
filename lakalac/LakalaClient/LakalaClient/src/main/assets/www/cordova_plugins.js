(function(){var a=window.cordova.require;var c=window.cordova.define;a("cordova");cordova.define("cordova/_native",function(e,d,f){});cordova._native=a("cordova/_native");var b=cordova._native;b.navigation={};b.navigation.setTitle=function(d){cordova.exec(null,null,"Navigation","setTitle",[d])};b.navigation.goBack=function(){cordova.exec(null,null,"Navigation","goBack",[])};b.navigation.finish=function(){cordova.exec(null,null,"Navigation","finish",[])};b.navigation.showBackButton=function(){cordova.exec(null,null,"Navigation","showBackButton",[])};b.navigation.hideBackButton=function(){cordova.exec(null,null,"Navigation","hideBackButton",[])};b.navigation.showActionButton=function(d){cordova.exec(null,null,"Navigation","showActionButton",[d])};b.navigation.hideActionButton=function(){cordova.exec(null,null,"Navigation","hideActionButton",[])};b.navigation.actionClick=function(){Application.navigation.onAction()};b.navigation.showMenu=function(e,d){cordova.exec(null,null,"Navigation","showMenu",[e,d])};b.navigation.hideMenu=function(){cordova.exec(null,null,"Navigation","hideMenu",[])};b.navigation.menuCallback=function(d){Application.navigation.menuCallback(d)};b.navigation.isWebGoBack=function(d){if(Application.keyboard&&Application.keyboard.current&&Application.keyboard.current.isHidden&&!Application.keyboard.current.isHidden()){Application.keyboard.current.hide();if(window.location.href.indexOf("index-android.html")!=-1&&d){return true}}return Application.navigation.nativeGoback()};b.navigation.exitApp=function(){cordova.exec(null,null,"Navigation","exitApp",[])};b.nativeinfo={};b.nativeinfo.device={};b.nativeinfo.app={};b.nativeinfo.get=function(){cordova.exec(function(e){var d=cordova._native.nativeinfo;d.device=e.device;d.app=e.app},null,"NativeInfo","get",[])}})();(function(){var a=cordova._native;a.dialog={};a.dialog.alert=function(c,b,d){cordova.exec(d,null,"Dialog","alert",[c,b])};a.dialog.confirm=function(e,b,f,d,c){cordova.exec(f,null,"Dialog","confirm",[e,b,d,c])};a.dialog.showProgress=function(b){cordova.exec(null,null,"Dialog","showProgress",[b])};a.dialog.hideProgress=function(){cordova.exec(null,null,"Dialog","hideProgress",[])};a.dialog.commonInput=function(c,d,b){cordova.exec(d,b,"Dialog","commonInput",c)};a.dialog.imageInput=function(c,e,d,b){cordova.exec(d,b,"Dialog","imageInput",[c,e])};a.dialog.payPassword=function(c,d,b){cordova.exec(d,b,"Dialog","payPassword",[c])};a.dialog.passwordInput=function(c,d,b){cordova.exec(d,b,"Dialog","passwordInput",[c])};a.dialog.customImgDialog=function(f,c,e,b,d){cordova.exec(d,null,"Dialog","customImgDialog",[f,c,e,b])};a.dialog.showSelect=function(f,c,d,e,b){cordova.exec(e,b,"Dialog","showSelect",[f,c,d])};a.dialog.selectPayment=function(e,c,d,b){cordova.exec(d,b,"Dialog","selectPayment",[e,c])};a.dialog.webDialog=function(b,c){cordova.exec(c,null,"Dialog","webDialog",[b])};a.dialog.dateDialog=function(c,d,b){cordova.exec(d,b,"Dialog","dateDialog",[c])};a.dialog.toast=function(b){cordova.exec(null,null,"Dialog","toast",[b])};a.httprequest={};a.httprequest.send=function(c,e,d,b){cordova.exec(d,b,"HttpRequest","request",[c,e])};a.httprequest.cancel=function(b){cordova.exec(null,null,"HttpRequest","cancel",[b])};a.session={};a.session.getSeries=function(b){cordova.exec(b,null,"Session","getSeries",[])};a.session.getSession=function(b){cordova.exec(b,null,"Session","getSession",[])};a.session.getUser=function(b){cordova.exec(b,null,"Session","getUser",[])};a.session.setUser=function(b){cordova.exec(null,null,"Session","setUser",[b])};a.businessparameter={};a.businessparameter.getAction=function(b){cordova.exec(b,null,"BusinessParameter","getAction",[])};a.businessparameter.getData=function(b){cordova.exec(b,null,"BusinessParameter","getData",[])};a.businessparameter.getPageData=function(b){cordova.exec(b,null,"BusinessParameter","getPageData",[])};a.businesslauncher={};a.businesslauncher.startPage=function(d,c,b){b=b===undefined?false:b;cordova.exec(null,null,"BusinessLauncher","startPage",[d,c,b])};a.businesslauncher.startPageForResult=function(e,d,b,c){cordova.exec(b,null,"BusinessLauncher","startPageForResult",[e,d,c])};a.businesslauncher.setResult=function(b,c){cordova.exec(null,null,"BusinessLauncher","setResult",[b,c])};a.businesslauncher.returnPage=function(b){cordova.exec(null,null,"BusinessLauncher","returnPage",[b])};a.businesslauncher.openWithNavigation=function(b,d,c){cordova.exec(null,null,"BusinessLauncher","openWithNavigation",[b,d,c])};a.phonebook={};a.phonebook.get=function(b,c){cordova.exec(c,null,"PhoneBook","get",[b])};a.phonebook.call=function(b){cordova.exec(null,null,"PhoneBook","call",[b])};a.phonebook.getName=function(c,b){cordova.exec(c,null,"PhoneBook","getName",[b])};a.database={};a.database.exec=function(d,c,e,b){cordova.exec(e,b,"DBPlugin","exec",[d,c])};a.database.select=function(d,c,e,b){cordova.exec(e,b,"DBPlugin","select",[d,c])};a.swipecard={};a.swipecard.launch=function(d,c,e,b){cordova.exec(d,c,"Swiper","swipe",[e,b])};a.swipecard.stop=function(){cordova.exec(null,null,"Swiper","stop",[])};a.swipecard.encrypt=function(d,c,b){cordova.exec(b,null,"Swiper","encrypt",[d,c])};a.swipecard.sendPin=function(b){cordova.exec(null,null,"Swiper","sendPin",[b])};a.swipecard.reEnterPin=function(){cordova.exec(null,null,"Swiper","reEnterPin",[])};a.swipecard.storeTc=function(b){cordova.exec(null,null,"Swiper","storeTC",[b])};a.swipecard.doSecondInssuance=function(c,b){cordova.exec(null,null,"Swiper","doSecondInssuance",[c,b])};a.swipecard.emvFinish=function(b){cordova.exec(null,null,"Swiper","emvFinish",[b])};a.cashier={};a.cashier.openApp=function(b,c){cordova.exec(null,null,"Cashier","openApp",[b,c])};a.analytics={};a.analytics.event=function(d,c,e,b){cordova.exec(null,null,"Analytics","event",[d,c,e,b])};a.notificationcenter={};a.notificationcenter.commonNotify=function(c,b){cordova.exec(null,null,"NotificationCenter","commonNotify",[c,b])};a.location={};a.location.location=function(c,b){cordova.exec(c,b,"Location","location",[])};a.location.locationDetail=function(c,b){cordova.exec(c,b,"Location","locationDetail",[])};a.saveInfo={};a.saveInfo.setValue=function(c,d,e,b){cordova.exec(null,b,"SaveInfoPlugin","setValue",[c,d,e])};a.saveInfo.getValue=function(c,d,e,b){cordova.exec(e,b,"SaveInfoPlugin","getValue",[c,d])};a.saveInfo.set=function(b,c){cordova.exec(null,null,"SaveInfoPlugin","set",[b,c])};a.saveInfo.get=function(d,b,c){cordova.exec(b,null,"SaveInfoPlugin","get",[d,c])};a.softKeyBoard={};a.softKeyBoard.show=function(){cordova.exec(null,null,"SoftKeyBoard","show",[])};a.softKeyBoard.hide=function(){cordova.exec(null,null,"SoftKeyBoard","hide",[])};a.camera={};a.camera.picker=function(d,c,b){cordova.exec(c,b,"Camera","picker",[d])};a.camera.clear=function(){cordova.exec(null,null,"Camera","clear",[])};a.openSystemApp={};a.openSystemApp.openBrowser=function(c,d,b){cordova.exec(d,b,"OpenSystemApp","openBrowser",[c])};a.config={};a.config.read=function(c,b){cordova.exec(b,null,"ConfigFile","read",[c])}})();