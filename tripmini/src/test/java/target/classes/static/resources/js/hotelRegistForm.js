	// 새로운 파일 입력 엘리먼트에 이벤트 리스너 추가
	function imageInput(containerId) {
	    $('#recimgfile' + containerId).one('change', function (event) {
	        handleImageUpload(event, containerId);
	    });
	}
	
	// handleImageUpload 함수 수정
	function handleImageUpload(event, containerId) {
	    const files = event.target.files;
	    const previewContainer = document.getElementById("preview-container" + containerId);
	    console.log(files, 'event.target.files');

	    // 이전 미리보기 이미지 제거
	    previewContainer.innerHTML = "";

	    // 선택한 이미지들에 대한 미리보기 생성
	    for (let i = 0; i < files.length; i++) {
	        const file = files[i];
	        const reader = new FileReader();
	        reader.onload = function (event) {
	            const imageDiv = document.createElement("div");
	            const imageUrl = event.target.result;

	            const previewImage = document.createElement("img");
	            previewImage.setAttribute("src", imageUrl);
	            previewImage.setAttribute("class", "preview-image");
	            imageDiv.appendChild(previewImage);

	            // 삭제 버튼 추가
	            const deleteButton = document.createElement("button");
	            deleteButton.setAttribute("class", "delete-button");
	            deleteButton.textContent = "삭제";
	            deleteButton.addEventListener("click", function () {
	                // 해당 이미지와 숨겨진 입력 요소를 미리보기에서 제거
	                imageDiv.remove();
	            });
	            imageDiv.appendChild(deleteButton);

	            // 숨겨진 입력 요소 추가
	            const imgHidden = document.createElement("input");
	            imgHidden.setAttribute("type", "hidden");
	            imgHidden.setAttribute("name", "rec_photo_nday");
	            imgHidden.setAttribute("value", containerId);
	            imageDiv.appendChild(imgHidden);

	            previewContainer.appendChild(imageDiv);
	        };
	        reader.readAsDataURL(file);
	    }
	}
	
	// 호텔 등급-----------------------------------------------------------------------
	const gradeSelect = document.getElementById('gradeSelect');
    const starDiv = document.getElementById('star-box');

    // 호텔 등급 변경 시 실행될 함수
    gradeSelect.addEventListener('change', function() {
        updateStars();
    });

    // 선택한 숫자에 따라 별 아이콘 업데이트 함수
    function updateStars() {
        const selectedValue = gradeSelect.value;
        starDiv.innerHTML = ''; // 기존 내용 초기화

        const starIcon = document.createElement('span');
        starIcon.className = 'star-icon';
        starIcon.textContent = '★'.repeat(selectedValue); // 선택한 숫자만큼 별 표시
        starDiv.appendChild(starIcon);
    }