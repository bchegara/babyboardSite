function com_mycompany_babyboardsite_AppWidgetSet(){var I='bootstrap',J='begin',K='gwt.codesvr.com.mycompany.babyboardsite.AppWidgetSet=',L='gwt.codesvr=',M='com.mycompany.babyboardsite.AppWidgetSet',N='startup',O='DUMMY',P=0,Q='body',R='iframe',S='javascript:""',T='position:absolute; width:0; height:0; border:none; left: -1000px;',U=' top: -1000px;',V='CSS1Compat',W='<!doctype html>',X='',Y='<html><head><\/head><body><\/body><\/html>',Z='undefined',$=/loaded|complete/,_='DOMContentLoaded',ab=50,bb='Chrome',cb='eval("',db=1,eb='");',fb='script',gb='javascript',hb='moduleStartup',ib='moduleRequested',jb='head',kb='meta',lb='name',mb='com.mycompany.babyboardsite.AppWidgetSet::',nb='::',ob='gwt:property',pb='content',qb='=',rb='gwt:onPropertyErrorFn',sb='Bad handler "',tb='" for "gwt:onPropertyErrorFn"',ub='gwt:onLoadErrorFn',vb='" for "gwt:onLoadErrorFn"',wb='#',xb='?',yb='/',zb=/^\w+:\/\//,Ab='img',Bb='clear.cache.gif',Cb='baseUrl',Db='com.mycompany.babyboardsite.AppWidgetSet.nocache.js',Eb='base',Fb='//',Gb=/^\//,Hb=/^[a-zA-Z]+:\/\//,Ib='user.agent',Jb='opera',Kb='webkit',Lb='safari',Mb='msie',Nb=10,Ob='ie10',Pb=9,Qb='ie9',Rb=8,Sb='ie8',Tb='gecko',Ub='gecko1_8',Vb='unknown',Wb=2,Xb=3,Yb=4,Zb=5,$b='selectingPermutation',_b='com.mycompany.babyboardsite.AppWidgetSet.devmode.js',ac='890BA2AF8B6EEEC45D6F0090065F7A45',bc=':1',cc=':2',dc=':3',ec=':4',fc=':5',gc=':',hc='.cache.js',ic='link',jc='rel',kc='stylesheet',lc='href',mc='loadExternalRefs',nc='fi_jasoft_dragdroplayouts/dragdroplayouts.css',oc='end';var o=window;var p=document;r(I,J);function q(){var a=o.location.search;return a.indexOf(K)!=-1||a.indexOf(L)!=-1}
function r(a,b){if(o.__gwtStatsEvent){o.__gwtStatsEvent({moduleName:M,sessionId:o.__gwtStatsSessionId,subSystem:N,evtGroup:a,millis:(new Date).getTime(),type:b})}}
com_mycompany_babyboardsite_AppWidgetSet.__sendStats=r;com_mycompany_babyboardsite_AppWidgetSet.__moduleName=M;com_mycompany_babyboardsite_AppWidgetSet.__errFn=null;com_mycompany_babyboardsite_AppWidgetSet.__moduleBase=O;com_mycompany_babyboardsite_AppWidgetSet.__softPermutationId=P;com_mycompany_babyboardsite_AppWidgetSet.__computePropValue=null;com_mycompany_babyboardsite_AppWidgetSet.__getPropMap=null;com_mycompany_babyboardsite_AppWidgetSet.__gwtInstallCode=function(){};com_mycompany_babyboardsite_AppWidgetSet.__gwtStartLoadingFragment=function(){return null};com_mycompany_babyboardsite_AppWidgetSet.__gwt_isKnownPropertyValue=function(){return false};com_mycompany_babyboardsite_AppWidgetSet.__gwt_getMetaProperty=function(){return null};__propertyErrorFunction=null;var s=o.__gwt_activeModules=o.__gwt_activeModules||{};s[M]={moduleName:M};var t;function u(){w();return t}
function v(){w();return t.getElementsByTagName(Q)[P]}
function w(){if(t){return}var a=p.createElement(R);a.src=S;a.id=M;a.style.cssText=T+U;a.tabIndex=-1;p.body.appendChild(a);t=a.contentDocument;if(!t){t=a.contentWindow.document}t.open();var b=document.compatMode==V?W:X;t.write(b+Y);t.close()}
function A(k){function l(a){function b(){if(typeof p.readyState==Z){return typeof p.body!=Z&&p.body!=null}return $.test(p.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(p.removeEventListener){p.removeEventListener(_,d,false)}if(e){clearInterval(e)}}}
if(p.addEventListener){p.addEventListener(_,d,false)}var e=setInterval(function(){if(b()){d()}},ab)}
function m(c){function d(a,b){a.removeChild(b)}
var e=v();var f=u();var g;if(navigator.userAgent.indexOf(bb)>-1&&window.JSON){var h=f.createDocumentFragment();h.appendChild(f.createTextNode(cb));for(var i=P;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(f.createTextNode(j.substring(db,j.length-db)))}h.appendChild(f.createTextNode(eb));g=f.createElement(fb);g.language=gb;g.appendChild(h);e.appendChild(g);d(e,g)}else{for(var i=P;i<c.length;i++){g=f.createElement(fb);g.language=gb;g.text=c[i];e.appendChild(g);d(e,g)}}}
com_mycompany_babyboardsite_AppWidgetSet.onScriptDownloaded=function(a){l(function(){m(a)})};r(hb,ib);var n=p.createElement(fb);n.src=k;p.getElementsByTagName(jb)[P].appendChild(n)}
com_mycompany_babyboardsite_AppWidgetSet.__startLoadingFragment=function(a){return D(a)};com_mycompany_babyboardsite_AppWidgetSet.__installRunAsyncCode=function(a){var b=v();var c=u().createElement(fb);c.language=gb;c.text=a;b.appendChild(c);b.removeChild(c)};function B(){var c={};var d;var e;var f=p.getElementsByTagName(kb);for(var g=P,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(lb),k;if(j){j=j.replace(mb,X);if(j.indexOf(nb)>=P){continue}if(j==ob){k=i.getAttribute(pb);if(k){var l,m=k.indexOf(qb);if(m>=P){j=k.substring(P,m);l=k.substring(m+db)}else{j=k;l=X}c[j]=l}}else if(j==rb){k=i.getAttribute(pb);if(k){try{d=eval(k)}catch(a){alert(sb+k+tb)}}}else if(j==ub){k=i.getAttribute(pb);if(k){try{e=eval(k)}catch(a){alert(sb+k+vb)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};__propertyErrorFunction=d;com_mycompany_babyboardsite_AppWidgetSet.__errFn=e}
function C(){function e(a){var b=a.lastIndexOf(wb);if(b==-1){b=a.length}var c=a.indexOf(xb);if(c==-1){c=a.length}var d=a.lastIndexOf(yb,Math.min(c,b));return d>=P?a.substring(P,d+db):X}
function f(a){if(a.match(zb)){}else{var b=p.createElement(Ab);b.src=a+Bb;a=e(b.src)}return a}
function g(){var a=__gwt_getMetaProperty(Cb);if(a!=null){return a}return X}
function h(){var a=p.getElementsByTagName(fb);for(var b=P;b<a.length;++b){if(a[b].src.indexOf(Db)!=-1){return e(a[b].src)}}return X}
function i(){var a=p.getElementsByTagName(Eb);if(a.length>P){return a[a.length-db].href}return X}
function j(){var a=p.location;return a.href==a.protocol+Fb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==X){k=h()}if(k==X){k=i()}if(k==X&&j()){k=e(p.location.href)}k=f(k);return k}
function D(a){if(a.match(Gb)){return a}if(a.match(Hb)){return a}return com_mycompany_babyboardsite_AppWidgetSet.__moduleBase+a}
function F(){var f=[];var g=P;function h(a,b){var c=f;for(var d=P,e=a.length-db;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var i=[];var j=[];function k(a){var b=j[a](),c=i[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(__propertyErrorFunc){__propertyErrorFunc(a,d,b)}throw null}
j[Ib]=function(){var a=navigator.userAgent.toLowerCase();if(function(){return a.indexOf(Jb)!=-1}())return Jb;if(function(){return a.indexOf(Kb)!=-1}())return Lb;if(function(){return a.indexOf(Mb)!=-1&&p.documentMode==Nb}())return Ob;if(function(){return a.indexOf(Mb)!=-1&&p.documentMode>=Pb}())return Qb;if(function(){return a.indexOf(Mb)!=-1&&p.documentMode>=Rb}())return Sb;if(function(){return a.indexOf(Tb)!=-1}())return Ub;return Vb};i[Ib]={gecko1_8:P,ie10:db,ie8:Wb,ie9:Xb,opera:Yb,safari:Zb};__gwt_isKnownPropertyValue=function(a,b){return b in i[a]};com_mycompany_babyboardsite_AppWidgetSet.__getPropMap=function(){var a={};for(var b in i){if(i.hasOwnProperty(b)){a[b]=k(b)}}return a};com_mycompany_babyboardsite_AppWidgetSet.__computePropValue=k;o.__gwt_activeModules[M].bindings=com_mycompany_babyboardsite_AppWidgetSet.__getPropMap;r(I,$b);if(q()){return D(_b)}var l;try{h([Ub],ac);h([Ob],ac+bc);h([Sb],ac+cc);h([Qb],ac+dc);h([Jb],ac+ec);h([Lb],ac+fc);l=f[k(Ib)];var m=l.indexOf(gc);if(m!=-1){g=parseInt(l.substring(m+db),Nb);l=l.substring(P,m)}}catch(a){}com_mycompany_babyboardsite_AppWidgetSet.__softPermutationId=g;return D(l+hc)}
function G(){if(!o.__gwt_stylesLoaded){o.__gwt_stylesLoaded={}}function c(a){if(!__gwt_stylesLoaded[a]){var b=p.createElement(ic);b.setAttribute(jc,kc);b.setAttribute(lc,D(a));p.getElementsByTagName(jb)[P].appendChild(b);__gwt_stylesLoaded[a]=true}}
r(mc,J);c(nc);r(mc,oc)}
B();com_mycompany_babyboardsite_AppWidgetSet.__moduleBase=C();s[M].moduleBase=com_mycompany_babyboardsite_AppWidgetSet.__moduleBase;var H=F();G();r(I,oc);A(H);return true}
com_mycompany_babyboardsite_AppWidgetSet.succeeded=com_mycompany_babyboardsite_AppWidgetSet();