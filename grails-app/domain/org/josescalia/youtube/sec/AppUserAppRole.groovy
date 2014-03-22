package org.josescalia.youtube.sec

import org.apache.commons.lang.builder.HashCodeBuilder

class AppUserAppRole implements Serializable {

	private static final long serialVersionUID = 1

	AppUser appUser
	AppRole appRole

	boolean equals(other) {
		if (!(other instanceof AppUserAppRole)) {
			return false
		}

		other.appUser?.id == appUser?.id &&
			other.appRole?.id == appRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (appUser) builder.append(appUser.id)
		if (appRole) builder.append(appRole.id)
		builder.toHashCode()
	}

	static AppUserAppRole get(long appUserId, long appRoleId) {
		AppUserAppRole.where {
			appUser == AppUser.load(appUserId) &&
			appRole == AppRole.load(appRoleId)
		}.get()
	}

	static AppUserAppRole create(AppUser appUser, AppRole appRole, boolean flush = false) {
		new AppUserAppRole(appUser: appUser, appRole: appRole).save(flush: flush, insert: true)
	}

	static boolean remove(AppUser u, AppRole r, boolean flush = false) {

		int rowCount = AppUserAppRole.where {
			appUser == AppUser.load(u.id) &&
			appRole == AppRole.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(AppUser u) {
		AppUserAppRole.where {
			appUser == AppUser.load(u.id)
		}.deleteAll()
	}

	static void removeAll(AppRole r) {
		AppUserAppRole.where {
			appRole == AppRole.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['appRole', 'appUser']
		version false
	}
}
