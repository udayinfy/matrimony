<%@tag pageEncoding="UTF-8" %>
<%@attribute name="head" fragment="true" %>
    <%@attribute name="middle" fragment="true" %>
        <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
            <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                        <jsp:useBean id="matrimony" class="com.matrimony.database.Matrimony" />
                        <jsp:useBean id="friendDAO" class="com.matrimony.database.FriendDAO" />
                        <c:set var="alias" value="matrimony" />
                        <t:layout>
                            <jsp:attribute name="head">
                                <link rel="stylesheet" type="text/css" href="/matrimony/resources/css/home-style.css">
                                <script src="/matrimony/resources/js/chat/entity.js"></script>
                                <script src="/matrimony/resources/js/chat/chat-process.js"></script>
                                <script src="/matrimony/resources/js/chat/emoticon.js"></script>
<!--                                 <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> -->
<!--                                 <script src="/matrimony/resources/js/friend.js" type="text/javascript"></script> -->
                                <jsp:invoke fragment="head"></jsp:invoke>
								
								<style>
								div.wrapper{
								  overflow:hidden; 
								  position:relative;  
								}
								div.caption {
								  position:relative; 
								  text-align:center; 
								  padding: 30px 15px;
								  background-image:url(http://demo.fearlessflyer.com/html/demo/pretty-hovers/images/wood.png);
								  
								}
								.st-friend-container div:hover{
								background-color:antiquewhite;
								}
								</style>
                                <script>
                                    var globalWSocket = new WebSocket('ws://localhost/matrimony/globalwsocket');
                                    globalWSocket.onopen = function() {
                                        var id = $('#mini-avatar').data('current-user-id');
                                        globalWSocket.send(id);
                                        console.log('connected to globalwsocket');
                                    }
                                    globalWSocket.onmessage = function(data) {
                                        processMessage(data);
                                    }
                                    globalWSocket.onclose = function() {
                                        processClose();
                                    }
                                    globalWSocket.onerror = function() {
                                        processError();
                                    }
//                                 	$.noConflict();
                                    $(document).ready(function(){

                                    	$("#avatar").click(function() {
                                            $("#uploadAvatarPhoto").click();
                                        });
                                        $("#uploadAvatarPhoto").change(function() {
                                            $("#uploadPhotoForm").submit();
                                        });
                                        $(document).on('click', '#btn-logout', function(e) {
                                            e.preventDefault();
                                            $("<form action='logout' method='POST'></form>").submit();
                                        });
                                        
                                        $('.wrapper').hover(
                                    			function(){
                                    				$(this).find('img').animate({opacity: ".8"}, 300);
                                    				$(this).find('.caption').animate({top:"-85px"}, 300);
                                    			},
                                    			function(){
                                    				$(this).find('img').animate({opacity: "1.0"}, 300);
                                    				$(this).find('.caption').animate({top:"85px"}, 100);
                                    			}
                                    		);
                                    });
                                </script>
                            </jsp:attribute>
                            <jsp:body>
                                <div id="hiddenDIV">
                                    <form id="uploadPhotoForm" action="changeAvatar" method="POST" enctype="multipart/form-data">
                                        <input id="uploadAvatarPhoto" type="file" name="file" accept="image/*" />
                                        <input type="hidden" type="text" name="test" value="love" />
                                    </form>
                                </div>
                                <div id="container" class=''>
                                    <div id='left' class='col-lg-2'>
                                    	<div class="wrapper">
											<a href='#'><img id='avatar' src='${userAvatarFolder}/${sessionScope.user.avatarPhoto }' style="opacity: 1; width: 100%;min-height: 150px;max-height: 350px;"></a>
											<div class="caption" style="top: 85px;"><input type='button' class='btn btn-success' value='Thay ảnh'/></div>
										</div>
                                        <span id='name' style='font-weight: 600;' class='col-lg-21'>${sessionScope.user.name }</span>
                                        <br/>
                                        <div class="list-group">
                                            <a class="list-group-item" href="/matrimony/update"><i class="fa fa-user"></i>&nbsp;Cập nhập t.tin</a>
                                            <a class="list-group-item" href="#"> <i class="fa fa-cog"></i>&nbsp;Cài đặt</a>
                                            <a class="list-group-item" href="/matrimony/payment"> <i class="fa fa-money"></i>&nbsp;Gia hạn tài khoản</a>
                                            <a class="list-group-item" href="/matrimony/paymentHistory"> <i class="fa fa-book"></i>&nbsp;Lịch sử t.toán</a>
                                            <a class="list-group-item" id='btn-logout' href="#"><i class="fa fa-sign-out"></i>&nbsp;Thoát</a>
                                        </div>
                                    </div>
                                    <div id='middle' class='col-lg-7' style='background-color: #ffffff; border: solid 2px #f4f4f4; border-radius: 6px;'>
                                        <jsp:invoke fragment="middle"></jsp:invoke>
                                    </div>

                                    <div id='right' class='col-lg-3'>
										<div  class='thumbnail' style='height: 100%;'>
										<h5 style='text-align: center;'>
										Những người đã làm quen
										</h5>
										<hr/>
										<div class='st-friend-container'>
											<c:forEach var="i" items="${friendDAO.getMyFriends(sessionScope.user) }">
												<div class='col-lg-12'>
												
													<img style='height: 40px;width: 40px;' src="/matrimony/resources/profile/avatar/${i.avatarPhoto }" />
														
														<span><a href='/matrimony/${i.username }'>${i.name }</a></span>
														<span style='float: right;'>
														<span style='float: right;padding-top: 10px;'>&nbsp;&nbsp;&nbsp;<i class='fa fa-circle' style='color:#0DC9F7;font-size: 11px;'></i></span>
														<a href="#" id='btn-chat-inbox' data-id='${i.id }' ><i style='font-size: 26px;' class="fa fa-commenting"></i></a></span>
														
												</div>
											</c:forEach>
		                                   <br/><br/><br/>
										</div>
										</div>
									</div>
                                    <!-- CHAT BOX START HERE -->
                                    <div style='z-index: 9999;'>
                                        <div id='chat-container' class="container">
                                            <div class="row col-lg-3 css-chat-window" id="chat-window" style='display: none;'>
                                                <div class="col-lg-12">
                                                    <div class="panel panel-default css-chat-panel">
                                                        <div class="panel-heading top-bar css-heading-chat" style='padding: 5px 5px 5px 0px; background-color:#160A47;'>
                                                            <div class="col-lg-8" style='padding-right:0;'>
                                                                <h3 class="panel-title">
	                              								<i class="fa fa-circle" style='font-size:11px; color:#0DC9F7;'></i>&nbsp;<span id='name' style='font-size: 13px;font-weight: 600;color: white;'>Unknow</span>
	                           								</h3>
                                                            </div>
                                                            <div class="col-lg-4" style="text-align: right; padding-right:0;">
                                                                <a href="#" style='color:#C8D1E4;'><span id="minim_chat_window"
											class="glyphicon glyphicon-minus icon_minim"></span></a>
                                                                <a href="#" style='color:#C8D1E4;'><span id="close_chat_window"
											class="glyphicon glyphicon-remove icon_close"
											data-id="chat-window"></span></a>
                                                            </div>
                                                        </div>
                                                        <div class="panel-body msg-container-base">
                                                            <div id='base-sent-message' class="row msg_container base-sent" style='display: none;'>
                                                                <div class="col-lg-10 css-chat-content">
                                                                    <div class="messages msg_sent">
                                                                        <p></p>
                                                                        <time datetime="2009-11-13T20:00">Timothy • 51 min</time>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 css-avatar">
                                                                    <img src="https://www.google.com/trends/resources/2327917647-google-icon.png" style='weight:44px; height:44px;' class="img-responsive img-sender">
                                                                </div>
                                                            </div>
                                                            <div id='base-receive-message' class="row msg_container base-receive" style='display: none;'>
                                                                <div class="col-lg-2 css-avatar">
                                                                    <img src="https://www.google.com/trends/resources/2327917647-google-icon.png" style='weight:44px; height:44px;' class="img-responsive img-receive">
                                                                </div>
                                                                <div class="col-md-10 col-xs-10 css-chat-content">
                                                                    <div class="messages msg_receive">
                                                                        <p></p>
                                                                        <time datetime="2009-11-13T20:00">Timothy • 51 min</time>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="panel-footer" style='padding:0px 0px 0px 5px;background-color:#ffffff;'>
                                                            <div class="input-group">
                                                                <input id="txt-chat-msg" type="text" style='height:100%;width: 100%;border: 0px;outline: none;' class="chat_input" />
                                                                <span class="input-group-btn">
                                                            	                          									 <button class="btn btn-primary btn-sm" id="btn-chat-send-msg">Send</button>
                                                            	                           								</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </jsp:body>
                        </t:layout>