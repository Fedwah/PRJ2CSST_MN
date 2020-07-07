<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*,java.text.*"%>




<title>Calendrier de maintenance</title>
<script>
	function goTo() {
		document.frm.submit()
	}
</script>
<form name="frm" method="post">

	<div>
		<table class="table" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>

							<td class="text-color paragraph" width="6%"><span
								style='position: relative; top: 6px; left: 0px;'>Année</span></td>
							<td width="10%"><select class="form-control" name="iYear"
								onchange="goTo()">

									<!-- start year and end year in combo box to change year in calendar -->
									<c:forEach var="i" begin="${cal.iTYear-70}"
										end="${cal.iTYear+70}">
										<option ${cal.iYear==i?"selected":""} value="${i}"><c:out
												value="${i}" /></option>
									</c:forEach>
							</select></td>
							<td width="64%" align="center"><h3 class="text-color">
									<c:out value="${mois}" />
									<c:out value="${cal.iYear}" />
								</h3></td>
							<td class="text-color paragraph" width="6%"><span
								style='position: relative; top: 6px; left: 0px;'>Mois</span></td>
							<td width="14%"><select class="form-control" name="iMonth"
								onchange="goTo()">

									<!--  print month in combo box to change month in calendar -->
									<c:forEach items="${months}" var="m">
										<option ${cal.iMonth==m.key?"selected":""} value="${m.key}"><c:out
												value="${m.value}" /></option>
									</c:forEach>

							</select></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td><table align="center" width="100%">
						<tbody>
							<tr align="center">
								<th scope="col">Dim</th>
								<th scope="col">Lun</th>
								<th scope="col">Mar</th>
								<th scope="col">Mer</th>
								<th scope="col">Jeu</th>
								<th scope="col">Ven</th>
								<th scope="col">Sam</th>
							</tr>
							<c:set var="cnt" value="${1}" />

							<c:forEach var="i" begin="1" end="${cal.iTotalweeks}">

								<tr>
									<c:forEach var="j" begin="1" end="7">
										<c:choose>

											<c:when
												test="${cnt<cal.weekStartDay || (cnt-cal.weekStartDay+1)>cal.days}">
												<td align="center" >&nbsp;</td>
											</c:when>
											<c:otherwise>
												<td align="center" width="14%" id="day_${cnt-cal.weekStartDay+1}">

													<c:set var="exist" value="${false}"></c:set> <c:forEach
														items="${main}" var="m">
														<c:choose>
															<c:when test="${m.getDay() == cnt-cal.weekStartDay+1}">
																<c:set var="maintenance" value="${m}"></c:set>
																<c:set var="exist" value="${true}"></c:set>
															</c:when>
														</c:choose>
													</c:forEach> 
													<c:choose>
														<c:when
															test="${cal.iTDay == cnt-cal.weekStartDay+1 && cal.iTMonth == cal.iMonth && cal.iTYear == cal.iYear }">
															<c:choose>
																<c:when test="${exist}">
																	<a type="button" class="btn btn-info rounded-circle round-btn" 
																		href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
																		<span>${cnt-cal.weekStartDay+1} </span>
																	</a>
																	<span class="badge badge-danger rounded-circle "
																		style='position: relative; top: -10px; left: -17px;'>!</span>
																</c:when>
																<c:otherwise>
																	<button type="button"
																		class="btn btn-info rounded-circle round-btn">
																		<span>${cnt-cal.weekStartDay+1} </span>
																	</button>
																</c:otherwise>
																
															</c:choose>

														</c:when>
														<c:when test="${exist && cal.getEtat(maintenance).equals('terminée') }">
															<a class="btn bg-color rounded-circle round-btn" style='color:white;'
																href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
																${cnt-cal.weekStartDay+1}
															</a>
														</c:when>
														<c:when
															test="${exist && cal.getEtat(maintenance).equals('pas encore réparré')}">
															<a type="button" class="btn notDoneYet rounded-circle round-btn" style='color:white;'
																href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
																<span>${cnt-cal.weekStartDay+1}</span>
															</a>
														</c:when>
														<c:when
															test="${exist && cal.getEtat(maintenance).equals('à venir')}">
															<a type="button" class="btn coming rounded-circle round-btn" 
																href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
																<span>${cnt-cal.weekStartDay+1}</span>
															</a>
														</c:when>
														<c:when
															test="${exist && cal.getEtat(maintenance).equals('en cours')}">
															<a type="button" class="btn en-cours rounded-circle round-btn" 
																href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
																<span>${cnt-cal.weekStartDay+1}</span>
															</a>
														</c:when>
														<c:otherwise>
															<span >${cnt-cal.weekStartDay+1}</span>
														</c:otherwise>
													</c:choose>
												</td>
											</c:otherwise>
										</c:choose>
										<c:set var="cnt" value="${cnt+1}" />

									</c:forEach>
							</c:forEach>
						</tbody>
					</table>
					</td>
			</tr>
		</table>
		<span class="paragraph ml-3">Code couleur : </span>
		<div class="row">

		<div class="col-6 ml-5">
		<span class="btn badge-info"> </span> Date d'aujourd'hui 
		</div>
		<div class="col-6">
		<span class="btn bg-color ml-5" > </span> <span>Maintenance terminée</span> 
		</div>
		<div class="col-6">
		<span class="btn en-cours"> </span> Maintenance en cours  
		</div>
		<div class="col-6">
		<span class="btn notDoneYet ml-5" > </span> <span>Date maintenance arrivée mais pas encore partie pour la réparation</span> 	
		</div>		
		<div class="col-6">
		<span class="btn coming" > </span> Maintenace à venir 
		</div>
		
		
		
		</div>
	</div>
</form>