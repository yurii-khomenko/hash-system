USE hash_system;

INSERT INTO users JSON '{
 "id": "9bed4bc3-98ee-4885-bb1f-087117016e10",
 "login": "reload-admin",
 "password": "reload-admin-password",
 "allowedIp": ["127.0.0.1","10.44.*.*","10.49.*.*","192.168.*.*"],
 "roles": ["reload"]}';

INSERT INTO users JSON '{
 "id": "8b472543-1810-4917-b98d-73bfd9adefb9",
 "login": "metric-prometeus",
 "password": "metric-prometeus-password",
 "allowedIp": ["127.0.0.1","10.44.*.*","10.49.*.*","192.168.*.*"],
 "roles": ["metric"]}';

INSERT INTO users JSON '{
  "id": "72a599ac-12b7-469a-86d9-c078b859c2dc",
  "login": "test-client",
  "password": "test-client-password",
  "allowedIp": ["127.0.0.1","10.44.*.*","10.49.*.*","192.168.*.*"],
  "roles": ["client"]}';