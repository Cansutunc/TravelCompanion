import React  from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export default function SidebarLink({ name, route, icon }) {
	return (
		<a className="sidebar-link" href={route}>
			<FontAwesomeIcon icon={icon} style={{ marginRight: "20px" }} />
			{name}
		</a>
	);
}
