
package com.github.epd.sprout.messages;

import java.util.Locale;

public enum Languages {
	ENGLISH("english", "", Status.REVIEWED, new String[]{"dachhack","00Evan"}, new String[]{"dachhack","00Evan"}),
	CHINESE("中文", "zh", Status.REVIEWED, new String[]{"g2159687"}, new String[]{"g2159687", "youxia5325", "破碎的像素地牢·翻译团队"}),
 CHINESET("中文繁體", "zht", Status.REVIEWED, new String[]{"那些回憶"}, new String[]{"那些回憶", "g2159687","youxia5325", "破碎的像素地牢·翻译团队"})
;

	public enum Status {
		//below 60% complete languages are not added.
		INCOMPLETE, //60-99% complete
		UNREVIEWED, //100% complete
		REVIEWED    //100% reviewed
	}

	private String name;
	private String code;
	private Status status;
	private String[] reviewers;
	private String[] translators;

	Languages(String name, String code, Status status, String[] reviewers, String[] translators) {
		this.name = name;
		this.code = code;
		this.status = status;
		this.reviewers = reviewers;
		this.translators = translators;
	}

	public String nativeName() {
		return name;
	}

	public String code() {
		return code;
	}

	public Status status() {
		return status;
	}

	public String[] reviewers() {
		if (reviewers == null) return new String[]{};
		else return reviewers.clone();
	}

	public String[] translators() {
		if (translators == null) return new String[]{};
		else return translators.clone();
	}

	public static Languages matchLocale(Locale locale) {
		return matchCode(locale.getLanguage());
	}

	public static Languages matchCode(String code) {
		for (Languages lang : Languages.values()) {
			if (lang.code().equals(code))
				return lang;
		}
		return ENGLISH;
	}

}
