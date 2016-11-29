package com.csit.project.components;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component("initUserandGroup")
public class InitUsersAndGroups implements CommandLineRunner {

	@Autowired
	private IdentityService identityService;

	@Override
	public void run(String... args) throws Exception {

		/*Group approverA = group("approverA");
		User userA = user("apvrA", "Emon", "Hossain");

		// Save user to the database for the first time
		identityService.createMembership(userA.getId(), approverA.getId());
		// personRepository.save(person1);

		Group approverB = group("approverB");
		User userB = user("apvrB", "Emon", "Hossain");

		// Save user to the database for the first time
		identityService.createMembership(userB.getId(), approverB.getId());

		Group approverC = group("approverC");
		User userC = user("apvrC", "Emon", "Hossain");

		// Save user to the database for the first time
		identityService.createMembership(userC.getId(), approverC.getId());*/

	}

	private Group group(String groupName) {
		Group group = identityService.newGroup(groupName);
		group.setName(groupName);
		group.setType("security-role");
		identityService.saveGroup(group);
		return group;
	}

	private User user(String userName, String f, String l) {
		User u = identityService.newUser(userName);
		u.setFirstName(f);
		u.setLastName(l);
		u.setPassword("password");
		identityService.saveUser(u);
		return u;
	}

}
