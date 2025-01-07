package com.demo.security01.entity.tag;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demo.security01.entity.user.User;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "USER_SKILLTAG")
@Entity
@NoArgsConstructor
@ToString
public class User_Skilltag {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_IDX")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SKILLTAG_IDX")
	private SkillTagEntity skillTag;

	public User getUser() {
		return user;
	}

	public SkillTagEntity getSkillTag() {
		return skillTag;
	}
	
	public void modifySkill(SkillTagEntity skillTag) {
		this.skillTag = skillTag;
	}

	@Builder
	public User_Skilltag(User user, SkillTagEntity skillTag) {
		this.user = user;
		this.skillTag = skillTag;
	}
	
	
	

}
