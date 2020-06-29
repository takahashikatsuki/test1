connection.end();
			function OnButtonClick(e) {
				const cnt = e.target.id.split('_')[1];
				const p1 = document.getElementById("p1_" + cnt);
				const a = document.getElementById("a_" + cnt);
				const update = document.getElementById("update_" + cnt);
				if (p1.style.display == "block") {
					// noneで非表示
					p1.style.display = "none";
					a.style.display = "block";
					update.style.display = "none";
					e.target.style.display = "block";
				} else {
					// blockで表示
					p1.style.display = "block";
					a.style.display = "none";
					update.style.display = "block";
					e.target.style.display = "none";
				}
			}/**
 *
 */