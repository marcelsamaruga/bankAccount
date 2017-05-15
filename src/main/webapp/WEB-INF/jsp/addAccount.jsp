<%@ include file="leftMenu.jsp" %>

<%@ include file="header.jsp" %>

<script type="text/javascript" src="resources/scripts/masks.js"></script>

<div id="createAccountPage">
	<table style="width: 99%;">
		<tr>
			<td width="20%">
				<span class="sb-content-desc">
			 		New Customer Account
			 	</span>
			</td>
		</tr>
	</table>
	<form action="addAccount" method="POST">
		<table style="width: 600px;">
			<tr>
				<td width="23%">
					<label>Customer CPF: </label>
				</td>
				<td width="25%" align="left">
					<form:input type="text" class="well-formated-input" id="ownerCpf" path="account.ownerCpf" size="30" maxlength="15"/>
				</td>
				<td align="left">
					<span class="sb-mandatory-info"> * (Must have 11 digits)</span>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right">
					<input class="sb-button" type="submit" value="Create Account" />
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<div>
						<div>
							<p>
							 	<span id="sb-return-message" class="${responseDTO.messageStyle}">
							 		${responseDTO.message}
							 	</span>
							</p>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>

<%@ include file="footer.jsp" %>