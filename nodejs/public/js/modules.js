var cM = this.map = cL.map;
this.overlay = cL;
this.render();
this._bind();
if (this._config.enableMaximize) {
	this.enableMaximize()
} else {
	this.disableMaximize()
}
this.setTitle(this._config.title);
this.setContent(this.content, true);
if (this._config.ifMaxScene) {
	this.setMaxScene(true)
}
this.redraw(null, true);
var T = cM.infoWindowDoms;
if (T) {
	if (cL instanceof ad) {
		T.marker = cL
	} else {
		T.marker = null
	}
}
function(){var
 cQ=this.map,cN=this,cL=cQ.infoWindowDoms,cR=cN.IMG_PATH;if(!cL){cL=cQ.infoWindowDoms={};cQ.infoWindow=cQ.infoWindowDoms;var
 cM=[\'<div class="BMap_shadow" style="position: absolute;display:none"
 type="infowindow_shadow">\'];cM.push(\'<div><img onmousedown="return false"
 style="left: -323px; top: 0px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -393px; top: 0px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -1033px; top: 0px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="top: -30px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -360px; top: -30px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="top: -30px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -14px; top: -310px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -255px; top: -310px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -440px; top: -310px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -255px; top: -310px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push(\'<div><img onmousedown="return
 false" style="left: -754px; top: -310px;"
 src="\'+cR+\'iws3.png"/></div>\');cM.push("</div>");cM.push(\'<div
 class="BMap_pop"
 style="position:absolute;display:none;cursor:default">\');cM.push(\'<div><div
 style="background:#01bafd;border-top:1px solid #16aeea;border-left:1px solid
 #16aeea;width:30px;height:30px;border-radius:8px"></div></div>\');cM.push(\'<div
 class="BMap_top"></div>\');cM.push(\'<div><div
 style="position:absolute;top:0;left:-6px;background:#01bafd;border-top:1px
 solid #16aeea;border-right:1px solid
 #16aeea;width:30px;height:30px;border-radius:8px"></div></div>\');cM.push(\'<div
 class="BMap_center"></div>\');cM.push(\'<div><div
 style="position:absolute;top:-6px;left:0;background:#01bafd;border-bottom:1px
 solid #16aeea;border-left:1px solid
 #16aeea;width:30px;height:30px;border-radius:8px"></div></div>\');cM.push(\'<div
 class="BMap_bottom"></div>\');cM.push(\'<div><div
 style="position:absolute;top:-6px;left:-6px;background:#01bafd;border-right:1px
 solid #16aeea;border-bottom:1px solid
 #16aeea;width:30px;height:30px;border-radius:8px"></div></div>\');cM.push(\'<div><img
 style="border:none;margin:0px;padding:0px;position:absolute;left:-186px;top:-691px;width:690px;height:786px"
 src="\'+cR+\'iw3.png"/></div>\');cM.push(\'<div
 style="overflow-y:hidde;overflow-x:hidde;width:auto;height:auto;position:absolute;left:16px;
 top:16px;z-index:10;"></div>\');cM.push("</div>");cL.popDom=at(cQ.platform,cM.join(""));cL.shadowDom=cL.popDom.previousSibling;cL.popDivs=cL.popDom.children;cL.shadowDivs=cL.shadowDom.getElementsByTagName("div");cL.contentMain=cL.popDivs[8];cL.titleDiv=at(cL.popDivs[8],\'<div
 class="BMap_bubble_title"
 style="display:block;overflow:hidden;height:24px;line-height:24px;white-space:nowrap"></div>\');cL.contentDiv=at(cL.popDivs[8],\'<div
 class="BMap_bubble_content"
 style="display:block"></div>\');cL.maxContentDiv=at(cL.popDivs[8],\'<div
 class="BMap_bubble_max_content"
 style="display:none;position:relative"></div>\');var
 T=10;if(a8.platform.isIphone||a8.platform.isAndroid){T=20}var cO=\'<img
 style="position:absolute;top:12px;width:\'+T+"px;height:"+T+\'px;-moz-user-select:none;cursor:pointer;z-index:10000;"
 src="\'+cR+\'iw_close1d3.png"/>\';cL.closeButton=at(cL.popDom,cO);var
 cP=\'<img
 style="position:absolute;top:12px;width:\'+T+"px;height:"+T+\'px;-moz-user-select:none;cursor:pointer;z-index:10000;display:none"
 src="\'+cR+\'iw_plus1d3.gif"/>\';cL.maxButton=at(cL.popDom,cP);this._mendIE6(cL)}cL.guid=cL.popDom.guid=this.guid},_mendIE6:function(cN){if(!a8.browser.ie||a8.browser.ie>6){return}var
 cM=cN.popDom.getElementsByTagName("IMG");for(var
 cL=0;cL<cM.length;cL++){if(cM[cL].src.indexOf(".png")<0){continue}cM[cL].style.cssText+=";FILTER:
 progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+cM[cL].src+",sizingMethod=crop)";cM[cL].src=this.IMG_PATH+"blank.gif"}var
 T=cN.shadowDom.getElementsByTagName("IMG");for(var
 cL=0;cL<T.length;cL++){T[cL].style.cssText+=";FILTER:
 progid:DXImageTransform.Microsoft.AlphaImageLoader(src="+T[cL].src+",sizingMethod=crop)";T[cL].src=this.IMG_PATH+"blank.gif"}},_bind:function(){var
 cQ=this,cR=cQ.map,cN=cR.infoWindowDoms,cM=cN.closeButton,cO=cN.popDom;var
 cL=function(cS){cQ.dispatchEvent(new
 bg("onclickclose"));cQ.overlay&&cQ.overlay.closeInfoWindow();co(cS)};cM.onclick=cL;var
 cP=["touchstart","touchmove","gesturestart","gesturechange","mousedown","mouseout","mouseover","click","mousewheel","keydown","selectstart"];a8.array.each(cP,function(cS){a8.on(cO,cS,aP)});a8.on(cO,"dblclick",co);a8.on(cO,"contextmenu",co);if(a8.browser.firefox>=4){a8.on(cO,"mouseup",function(cS){if(cS.button==2){co(cS)}})}if(window.addEventListener){cO.addEventListener("DOMMouseScroll",aP,false)}var
 T=function(cT){var
 cS=cQ.map.infoWindowDoms.maxButton;if(!cQ.isWinMax){cQ.maximize()}else{cQ.restore()}co(cT)};cN.maxButton.onclick=T;if(aA()){a8.on(cM,"touchend",cL);a8.on(cN.maxButton,"touchend",T)}cO=cM=cN=null},_setWinSize:function(cT,cS){var
 c3=this._config;cT=cT||c3.width;cS=cS||c3.height;if(cS<0){cS=0}var
 cN=c3.offset.width;var cR=c3.offset.height;var
 c2=[25,-1,25,-1,25,-1,25,34];var
 cM=[25,-1,25,-1,25,-1,25,50];c2[1]=cT-c2[0]-c2[2];if(a8.browser.ie&&document.compatMode!="CSS1Compat"){c2[3]=cT}else{c2[3]=cT-2}c2[5]=cT-c2[4]-c2[6];cM[1]=cM[0];cM[3]=cS-cM[0]-cM[4];if(a8.browser.ie&&document.compatMode!="CSS1Compat"){cM[5]=cM[4]}else{cM[5]=cM[4]-1}var
 c4=[0,c2[0],cT-c2[2],0,0,c2[4],cT-c2[6],Math.ceil((cT-c2[7])/2)];var
 cY=[0,0,0,cM[0],cS-c2[4],cS-c2[4],cS-c2[4],cS-c2[4]];var
 cL=this._allPopLeft=cN-Math.round((cT-c2[7])/2+15);var
 cZ=this._allPopTop=cR-cS-40;var c5=Math.floor((cS+cM[7])/2.03)+30;var
 cP=[70,-1,70,-1,-1,-1,50,-1,140,-1,70];var
 cX=[30,30,30,25,25,25,60,60,60,60,60];cP[7]=Math.round((cT+80-(cP[6]+cP[8]+cP[10])-50)/2);cP[9]=cP[7]+50;var
 c1=cP[6]+cP[7]+cP[8]+cP[9]+cP[10];cP[1]=c1-cP[0]-cP[2]-29;cP[5]=cP[3]=c5-cX[0]-cX[6]+70;cX[3]=cX[4]=cX[5]=c5-cX[0]-cX[6];cP[4]=(cP[0]+cP[1]+cP[2]+cX[3]+29)-cP[5]-cP[3];var
 T=[c5-60-1,c5-60-1+cP[0],c5-60-1+cP[0]+cP[1],29,29+cP[3],29+cP[3]+cP[4],0,cP[6],cP[6]+cP[7],cP[6]+cP[7]+cP[8],cP[6]+cP[7]+cP[8]+cP[9]];var
 cW=[0,0,0,cX[0],cX[0],cX[0],cX[0]+cX[3],cX[0]+cX[3],cX[0]+cX[3],cX[0]+cX[3],cX[0]+cX[3]];this._allShadowLeft=cN-cP[6]-cP[7]-70;this._allShadowTop=cR-c5+30;var
 cO=323-c5+90;shadowRightImageLeft=740+cO;var c0=function(c6){return"overflow:
 hidden; z-index: 1; position: absolute; left:"+c4[c6]+"px; top:"+cY[c6]+"px;
 width:"+c2[c6]+"px; height:"+cM[c6]+"px"};var
 cU=function(c6){return"overflow: hidden; z-index: 1; position: absolute;
 left:"+T[c6]+"px; top:"+cW[c6]+"px; width:"+cP[c6]+"px;
 height:"+cX[c6]+"px;"};var
 cQ=this.map.infoWindowDoms;if(cQ&&cQ.popDivs){for(var
 cV=0;cV<8;cV++){cQ.popDivs[cV].style.cssText=c0(cV)}}if(cQ&&cQ.shadowDivs){for(var
 cV=0;cV<cQ.shadowDivs.length;cV++){cQ.shadowDivs[cV].style.cssText=cU(cV)}cQ.shadowDivs[3].firstChild.style.left="-"+cO+"px";cQ.shadowDivs[5].firstChild.style.left="-"+shadowRightImageLeft+"px"}this.setPosition()},setWidth:function(T){T=T*1;if(!T&&T!=0||isNaN(T)||T<0){return}if(T!=0){if(T<220){T=220}if(T>730){T=730}}this._config.width=T;if(this._isMyDom()&&this.isOpen()){var
 cL=this;this.redraw(function(){cL.setPanToWithDelay()})}},setHeight:function(T){T=T*1;if(!T&&T!=0||isNaN(T)||T<0){return}if(T!=0){if(T<60){T=60}if(T>650){T=650}}this._config.height=T;var
 cM=this.map;if(this._isMyDom()&&this.isOpen()){if(this._config.width!=0){cM.infoWindowDoms.contentDiv.style.width=this._config.width+"px"}var
 cL=this;this.redraw(function(){cL.setPanToWithDelay()})}},setMaxWidth:function(T){T=T*1;if(!T&&T!=0||isNaN(T)||T<0){return}if(T!=0){if(T<220){T=220}if(T>730){T=730}}this._config.maxWidth=T;if(this.isWinMax){this.redraw()}},setTitle:function(cN){this._config.title=cN;if(!this._isMyDom()){return}var
 cM=this.map;var
 cL=cM.infoWindowDoms.titleDiv;if(!cN){a8.dom.hide(cL)}else{if(b3(cN)){cL.innerHTML=cN}else{cL.innerHTML="";cL.appendChild(cN)}a8.dom.show(cL)}var
 T=this;this.redraw(function(){T.setPanToWithDelay()})},setContent:function(cN,cP){this.content=cN;if(!this._isMyDom()){return}if(this.isWinMax){return}var
 cO=this.map;var cL=cO.infoWindowDoms.contentDiv;var
 T=cO.infoWindowDoms.maxContentDiv;if(b3(cN)){cL.innerHTML=cN}else{cL.innerHTML="";cL.appendChild(cN)}if(this._config.width!=0){cL.style.width=this._config.width+"px"}T.style.display="none";cL.style.display="";if(!cP){var
 cM=this;this.redraw(function(){cM.setPanToWithDelay()})}},setMaxContent:function(cL){if(!cL){cL=this._config.maxContent}else{this._config.maxContent=cL}var
 cM=this.map;if(!this._isMyDom()){return}var
 T=cM.infoWindowDoms;T.maxContentDiv.innerHTML=cL;if(!this.isWinMax){return}T.contentDiv.style.display="none";T.maxContentDiv.style.display=""},redraw:function(cU,cQ){if(!this._isMyDom()){return}if(!cQ&&!this.isOpen()){return}var
 cM=this,cP=1,cN=cM.map.infoWindowDoms,cL=0;cU=cU||function(){};if(cN.titleDiv.style.display!="none"){cL=24}var
 cO=7,cT=20;if(a8.platform.isIphone||a8.platform.isAndroid){cO=-1;cT=25}if(this.isWinMax){cS=cM._config.maxWidth;setTimeout(function(){var
 cW=cL+cN.maxContentDiv.scrollHeight;cW=cW>cM.map.height?cM.map.height-60:cW;cS=cS<220?220:cS;cS=cS>600?600:cS;cW=cW<55?55:cW;cW=cW>440?440:cW;cM._setWinSize(cS+32,cW+32);cN.contentMain.style.width=cS+"px";cN.contentMain.style.height=cW+"px";cN.closeButton.style.left=cS+cO+"px";cN.maxButton.style.left=cS-cT+cO+"px";cN.contentMain.style.overflow="hidden";cM.dispatchEvent(new
 bg("onresize"));cU()},cP)}else{var
 cV=cN.contentDiv.style,cR=cN.titleDiv.style,T=cN.contentMain.style;cV.width=T.width=cR.width="auto";cV.height=T.height=cR.height="auto";cV.whiteSpace="nowrap";if(cN.popDom.style.display=="none"){this.show()}cN.popDom.style.visibility="hidden";cN.shadowDom.style.visibility="hidden";var
 cS=cN.contentMain.clientWidth||0;cS=cM._config.width==0?cS:cM._config.width;cS=cS<220?220:cS;cS=cS>600?600:cS;T.width=cS+"px";h=cN.contentMain.scrollHeight||0;h=cM._config.height==0?h:cM._config.height;cM._setWinSize(cS+32,h+32);setTimeout(function(){cV.whiteSpace="";if(cM._config.width==0){T.overflowX="hidden"}else{T.overflowX="auto"}if(cM._config.height==0){T.overflowY="hidden"}else{T.overflowY="auto"}h=cN.contentMain.scrollHeight||0;h=cM._config.height==0?h:cM._config.height;h=h<55?55:h;h=h>440?440:h;cM._setWinSize(cS+32,h+32);cN.popDom.style.visibility="";cN.shadowDom.style.visibility="";T.height=h+"px";cN.closeButton.style.left=cS+cO+"px";cN.maxButton.style.left=cS-cT+cO+"px";cM.dispatchEvent(new
 bg("onresize"));cU()},cP)}},setPosition:function(){if(!this._isMyDom()){return}var
 T=this.map.infoWindowDoms,cL=this.overlay,cN=this.map.pointToOverlayPixel(cL.getPosition()),cM=cL.getIcon(),cO=new
 bu(cN.x-cM.anchor.width+cM.infoWindowAnchor.width+cL.getOffset().width,cN.y-cM.anchor.height+cM.infoWindowAnchor.height+cL.getOffset().height);T.popDom.style.left=this._allPopLeft+cO.x+"px";T.popDom.style.top=this._allPopTop+cO.y+"px";T.shadowDom.style.left=this._allShadowLeft+cO.x+"px";T.shadowDom.style.top=this._allShadowTop+cO.y+"px"},setPanToWithDelay:function(T){var
 cL=this;setTimeout(function(){cL.setPanTo()},T||200)},setPanTo:function(){if(!this.overlay||!this.overlay.getPosition()||!this._config.enableAutoPan||!this._isMyDom()){return}var
 c0=this.map;var cS=c0.infoWindowDoms;var cM=cS.popDivs;var
 c1=cS.popDom;if(!cM||!c1){return}var cN=parseInt(cM[3].style.width)+2;var
 cX=parseInt(cM[1].style.height)+parseInt(cM[3].style.height)+parseInt(cM[7].style.height);var
 cV=parseInt(c1.style.left)+this.map.offsetX;var
 cP=parseInt(c1.style.top)+this.map.offsetY;var cR=new bu(cV,cP);var cQ=new
 bu(cN+cV,cX+cP);if(this._config.height!=0&&document.all){if(!c0.temp.infoKey){c0.temp.infoKey=-1}var
 cY=-c0.temp.infoKey;c0.temp.infoKey=-c0.temp.infoKey}var cY=0;var cW=0;var
 T=10;var cL=this._config.margin[0];var cO=this._config.margin[1];var
 cZ=this._config.margin[2];var
 cT=this._config.margin[3];if(cR.x<cT){cY=-cR.x+cT}if(cR.y<cL){cW=-cR.y+cL}if(cQ.x>c0.width-cO){cY=c0.width-cQ.x-cO}if(cQ.y>c0.height-cZ){cW=c0.height-cQ.y-cZ}this._loadCollisions();var
 cU=this._config.collisions;if(cR.x<cU[0][0]&&cR.y<cU[0][1]){if(Math.abs(-cR.x+cU[0][0])<Math.abs(-cR.y+cU[0][1])){cY=-cR.x+cU[0][0]}else{if(c0.height-cU[0][1]-cU[3][1]<cX){cY=-cR.x+cU[0][0]}else{cW=-cR.y+cU[0][1]}}if(c0.width-cU[0][0]-cU[1][0]<cN&&cR.y<cU[1][1]){cW=-cR.y+cU[1][1]}}if(cQ.x>c0.width-cU[1][0]&&cR.y<cU[1][1]){if(Math.abs(-cQ.x+c0.width-cU[1][0])<Math.abs(-cR.y+cU[1][1])&&c0.width-cU[0][0]-cU[1][0]>=cN){cY=-cQ.x+c0.width-cU[1][0]}else{cW=-cR.y+cU[1][1];if(c0.width-cU[0][0]-cU[1][0]<cN&&c0.width-cU[0][0]<cN){cY=-cR.x+cU[0][0]}}}if(cR.x<cU[3][0]&&cQ.y>c0.height-cU[3][1]){if(Math.abs(-cR.x+cU[3][0])<Math.abs(-cQ.y+c0.height-cU[3][1])&&(Math.abs(-cR.x+cU[3][0])<Math.abs(cW)&&cW!=0||cW==0)&&c0.width-cU[3][0]>=cN){cY=-cR.x+cU[3][0]}else{cW=-cQ.y+c0.height-cU[3][1]}if(c0.height-cU[0][1]-cU[3][1]<cX&&cR.x<cU[0][0]){cY=-cR.x+cU[0][0]}}if(cQ.x>c0.width-cU[2][0]&&cQ.y>c0.height-cU[2][1]){if(Math.abs(-cQ.x+c0.width-cU[2][0])<Math.abs(-cQ.y+c0.height-cU[2][1])&&(Math.abs(-cQ.x+c0.width-cU[2][0])<Math.abs(cW)&&cW!=0||cW==0)&&c0.width-cU[0][0]-cU[1][0]>=cN){cY=-cQ.x+c0.width-cU[2][0]}else{if(c0.height-cU[1][1]-cU[2][1]>=cX){cW=-cQ.y+c0.height-cU[2][1]}else{cW=-cR.y+cU[1][1]}if(c0.width-cU[0][0]-cU[2][0]<cN){cY=-cR.x+cU[0][0]}}}if(cY!=0||cW!=0){c0.panBy(cY,cW)}},_loadCollisions:function(){if(!this.map){return}var
 cL=this.map;this._config.collisions=[[10,10],[10,10],[10,10],[10,10]];var
 cU=this.map.container;for(var cS=0,cM=cU.children.length;cS<cM;cS++){var
 cP,cV,cY=!!(ch(cU.children[cS]._anchor)&&cU.children[cS]._offset);if(cU.children[cS]._jsobj&&cU.children[cS]._jsobj
 instanceof
 cp&&cU.children[cS]._jsobj.blockInfoWindow==true){cP=cU.children[cS]}else{if(cY){cP=cU.children[cS]}else{continue}}var
 cW=cP.offsetWidth,cT=cP.offsetHeight,T=cP._jsobj,cO,cR;if(!T||cY){if(ch(cP._anchor)&&cP._offset&&aJ(cP).display!="none"&&aJ(cP).visibility!="hidden"){cO=cP._offset;cR=cP._anchor}else{continue}}else{if(T.isVisible()==false){continue}cO=T.getOffset();cR=T.getAnchor()}switch(cR){case
 BMAP_ANCHOR_TOP_LEFT:cV=0;break;case BMAP_ANCHOR_TOP_RIGHT:cV=1;break;case
 BMAP_ANCHOR_BOTTOM_LEFT:cV=3;break;case
 BMAP_ANCHOR_BOTTOM_RIGHT:cV=2;break;default:break}var
 cQ=cW+cO.width+10,cN=cT+cO.height+10,cX=this._config.collisions[cV];this._config.collisions[cV]=[cQ>cX[0]?cQ:cX[0],cN>cX[1]?cN:cX[1]]}},enableMaximize:function(){this._config.enableMaximize=true;if(this._isMyDom()){this.map.infoWindowDoms.maxButton.style.display="block"}},disableMaximize:function(){this._config.enableMaximize=false;if(this._isMyDom()){this.map.infoWindowDoms.maxButton.style.display="none"}},show:function(){if(!this._isMyDom()){return}var
 cL=this.map.infoWindowDoms;if(cL.popDom.style.display!="none"){return}if(cJ(this.content)){cL.contentDiv.appendChild(this.content)}if(cJ(this._config.title)){cL.titleDiv.appendChild(this._config.title)}a8.dom.show(cL.popDom);a8.dom.show(cL.shadowDom);var
 T=new
 bg("onopen");T.point=this.getPosition();this.dispatchEvent(T);this.redraw()},hide:function(){if(!this._isMyDom()){return
 false}var
 cL=this.map.infoWindowDoms;if(cL.popDom.style.display=="none"){return
 false}if(this._config.onClosing()==false){return
 false}if(cJ(this.content)){cL.contentDiv.removeChild(this.content)}if(cJ(this._config.title)){cL.titleDiv.removeChild(this._config.title)}a8.dom.hide(cL.popDom);a8.dom.hide(cL.shadowDom);if(this.isWinMax){this.isWinMax=false;cL.maxContentDiv.style.display="none";cL.contentDiv.style.display="";cL.maxButton.src=this.IMG_PATH+"iw_plus1d3.gif"}var
 T=new
 bg("onclose");T.point=this.getPosition();this.dispatchEvent(T);if(this.map.temp._clickCloseBindTimer){clearTimeout(this.map.temp._clickCloseBindTimer);this.map.temp._clickCloseBindTimer=null}else{this.map.removeEventListener("click",this.map.temp._clickCloseHandler);this.map.temp._clickCloseBinded=false}a8.lang.decontrol(this.guid);return
 true},maximize:function(){if(!this.map||!this.isOpen()||!this._config.enableMaximize||this.isWinMax){return}if(!this._isMyDom()){return}var
 cL=this.map.infoWindowDoms.maxButton;var
 T=this;T.isWinMax=true;cL.src=T.IMG_PATH+"iw_minus1d3.gif";T.setMaxContent();T.map.infoWindowDoms.maxContentDiv.style.display="block";T.redraw();T.dispatchEvent(new
 bg("onmaximize"));T.setPanToWithDelay()},restore:function(){if(!this.map||!this.isOpen()||!this.isWinMax){return}if(!this._isMyDom()){return}this.isWinMax=false;var
 T=this;var
 cL=T.map.infoWindowDoms.maxButton;cL.src=T.IMG_PATH+"iw_plus1d3.gif";T.setContent(this.content,true);T.map.infoWindowDoms.maxContentDiv.style.display="none";T.redraw();T.dispatchEvent(new
 bg("onrestore"));T.setPanToWithDelay()},_revert:function(){if(!this._isMyDom()){return}this.isWinMax=false;var
 T=this.map.infoWindowDoms;T.titleDiv.innerHTML="";T.contentDiv.innerHTML="";T.maxContentDiv.innerHTML="";T.maxButton.src=this.IMG_PATH+"iw_plus1d3.gif"},_setOverflow:function(){var
 cM=this.map;if(!this._isMyDom()){return}var
 cL=cM.infoWindowDoms,T=cL.contentMain.style;cL._overflowX=T.overflowX;cL._overflowY=T.overflowY;T.overflowX="hidden";T.overflowY="hidden"},_resetOverflow:function(){var
 cM=this.map;if(!this._isMyDom()||!cM.infoWindowDoms._overflowX||!cM.infoWindowDoms._overflowY){return}var
 cL=cM.infoWindowDoms,T=cL.contentMain.style;T.overflowX=cL._overflowX;T.overflowY=cL._overflowY;delete
 cL._overflowX;delete cL._overflowY},isOpen:function(){if(!this.map){return
 false}var T=this.map.temp.infoWin;if(!T){return
 false}if(!this._isMyDom()){return
 false}if(T&&T.overlay===this.overlay&&this.map.infoWindowDoms&&this.map.infoWindowDoms.popDom.style.display=="none"){return
 false}else{return true}},setMaxScene:function(cL){var
 cM=this;if(!this._isMyDom()){return}var
 T=cM.map.infoWindowDoms;T.maxButton.style.display="block";var
 cN=T.maxButton;if(!!cL==!!this.isWinMax){return}if(cL){cM.isWinMax=true;cN.src=cM.IMG_PATH+"iw_minus1d3.gif";cM.setMaxContent();T.maxContentDiv.style.display="block"}else{cM.isWinMax=false;cN.src=cM.IMG_PATH+"iw_plus1d3.gif";cM.setContent(this.content,true);T.maxContentDiv.style.display="none"}this.redraw()},_draw:function(){if(this._visible==true&&this.overlay){this.overlay.openInfoWindow(this)}},_isMyDom:function(){return(this.map&&this.map.infoWindowDoms&&this.map.infoWindowDoms.guid==this.guid)}});Y.prototype.openInfoWindow=function(cN){var
 cM=this.map;if(!cM||!this.domElement||this.isVisible()==false||!cN instanceof
 bP){return}var
 cL=cM.temp;if(cL.infoWin&&cL.infoWin.overlay&&cL.infoWin.overlay._fromMap){cM.closeInfoWindow()}if(cL.infoWin===cN&&cL.infoWin.isOpen()&&cL.infoWin.overlay===this){cN.setPanToWithDelay();return}cM.closeInfoWindow();this.infoWindow=cN;if(cL.infoWin==null||cL.infoWin!=cN){if(cM.infoWindowDoms){cM.infoWindowDoms.closeButton.onclick=null;cM.infoWindowDoms.maxButton.onclick=null}cL.infoWin=cN;cN.initialize(this)}else{cN.redraw(null,true)}a8.lang.Class.call(cN,cN.guid);if(!cL._clickCloseHandler){cL._clickCloseHandler=function(cO){if(!cO.overlay){if(cM.temp.infoWin&&cM.temp.infoWin._config.enableCloseOnClick){cM.closeInfoWindow();cM.removeEventListener("click",arguments.callee);cL._clickCloseBinded=false}}}}if(!cL._clickCloseBinded){cL._clickCloseBindTimer=setTimeout(function(){cM.addEventListener("click",cL._clickCloseHandler);cL._clickCloseBinded=true;cL._clickCloseBindTimer=null},200)}if(cL._infoWin){delete
 cL._infoWin}cN.overlay=this;var
 T=cM.infoWindowDoms;this.map.getPanes().floatPane.appendChild(T.popDom);this.map.getPanes().floatShadow.appendChild(T.shadowDom);cN.setPanToWithDelay();this.dispatchEvent(new
 bg("oninfowindowopen"))};Y.prototype.closeInfoWindow=function(){if(!this.map||!this.map.infoWindowDoms){return}var
 cL=this;if(cL.infoWindow&&cL.infoWindow.guid==cL.map.infoWindowDoms.guid){try{if(cL.infoWindow.hide()==true){cL.dispatchEvent(new
 bg("oninfowindowclose"));cL.map.temp.infoWin=cL.infoWindow=null}}catch(T){}};
