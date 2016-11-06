package cn.tata.t2s.ssm.entity;


public class WeChatOAuth2Token {
	private String accessToken;

	private int expiresIn;

	private String refreshToken;

	private String openid;

	private String scope;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefeshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return String.format(
				"WeChatOAuth2Token  [\n\taccessToken=%s;\n\texpiresIn=%s;\n\trefeshToken=%s;\n\topenid=%s;\n\tscope=%s\n]",
				accessToken, expiresIn, refreshToken, openid, scope);
	}
	
	
}
