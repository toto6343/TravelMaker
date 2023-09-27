//$('#ckBox').is(':checked');
   window.addEventListener('DOMContentLoaded', function() {
      var form = document.querySelector('form'); // 폼 요소를 가져옵니다
      var inputInSecondRow = document.querySelector('tr.mtrtr:nth-child(2) input[name="plan_mtr_material"]');
      var hiddenInputInThirdRow = document.querySelector('tr.mtrtr:nth-child(3) input[name="plan_mtr_class"]');
      
      form.addEventListener('submit', function(event) {
        // 두 번째 행의 입력 요소에서 값을 가져옵니다
         var inputValue = inputInSecondRow.value;
          
          // 세 번째 행의 숨겨진 입력 요소에 값을 설정합니다
          hiddenInputInThirdRow.value = inputValue;
      });
   });
// 새로운 파일 입력 엘리먼트에 이벤트 리스너 추가
function imageInput() {
    $('#recimgfile').one('change', function (event) {
        handleImageUpload(event);
    });
}

// handleImageUpload 함수 수정
function handleImageUpload(event) {
   const files = event.target.files;
   const previewContainer = document.getElementById("preview-container");
    console.log(files, 'event.target.files');

    // 이전 미리보기 이미지 제거
    //previewContainer.innerHTML = "";

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


            previewContainer.appendChild(imageDiv);
        };
        reader.readAsDataURL(file);
    }
}
   /* 준비물 추가 및 삭제 함수*/
   var addmtrnum = 2; // 준비물 분류 추가버튼
   
   // 준비물 추가 함수
   function addmtr(num) {
       const tableId = "drag-table"+num;
       const table = document.getElementById(tableId);
       const newRow = document.createElement("tr");
       newRow.setAttribute("class", "mtrtr");
       newRow.innerHTML = `<td class="mtrtd">
                         <button type="button" onclick="delmtrtr(this)">X</button>
                         <input type="text" placeholder="준비물 " class="mtr" name="plan_mtr_material">
                         <input type="checkbox" class="mtrcheck" name="plan_mtr_check" value="0">
                      </td>`;
       table.querySelector("tbody").appendChild(newRow);
     }
   // 준비물 삭제 함수
    function delmtrtr(button) {
       var row = button.parentElement.parentElement;
        var rows = row.parentElement.querySelectorAll('.mtrtr');
        console.log(rows.length)
        if (rows.length > 2) {
           row.remove();
        }else{
            alert("더 이상 준비물을 삭제할 수 없습니다.");
        }
    }
   // 준비물 분류 추가 함수
    function addTable(){
       console.log(addmtrnum);
       if(addmtrnum < 6){
          var addTableTarget =`
              <div class="mtrdiv">
               <table id="drag-table`+addmtrnum+`" class="mtrtable">
               <tr class="mtrtr">
                 <th class="mtrth">
                    <button type="button" onclick="delmtrtable(this)">X</button>
                    <input type="text" placeholder="준비물 분류" name="plan_mtr_class">
                    <button type="button" class="add-row-button1" onclick="addmtr(`+addmtrnum+`)">추가</button>
                 </th>
               </tr>
               <tr class="mtrtr">
                 <td class="mtrtd">
                    <button type="button" onclick="delmtrtr(this)">X</button>
                    <input type="text" placeholder="준비물 " class="mtr" name="plan_mtr_material">
                    <input type="checkbox" class="mtrcheck" name="plan_mtr_check" value="0">
                 </td>
               </tr>
               </table>
           </div>`;
          $('#targetTable').append(addTableTarget);
          addmtrnum++
       }
       else{
          alert("최대 5개까지 가능합니다.");
       }
    }
   // 준비물 분류 삭제 함수
    function delmtrtable(button) {
        var tableDiv = button.closest('.mtrdiv');
        var tableDivs = document.querySelectorAll('.mtrdiv');
        console.log("tableDivs.length"+tableDivs.length)
        if (tableDivs.length > 1) {
            tableDiv.remove();
            addmtrnum--
        }else{
            alert("더 이상 준비물분류를 삭제할 수 없습니다.");
        }
    }
    var data = {
      labels: ['Red', 'Blue', 'Yellow'],
      datasets: [{
        data: [300, 50, 100],
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
        hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
      }]
    };

    var options = {
      maintainAspectRatio: false, // 차트의 가로 세로 비율을 유지하지 않습니다.
      responsive: true, // 차트 크기를 뷰포트에 따라 자동으로 조절합니다.
      cutoutPercentage: 100, // 도넛의 크기 비율을 조절합니다. 0에서 100까지 값을 지정할 수 있습니다.
    };

    var ctx = document.getElementById('donutChart').getContext('2d');
    var donutChart = new Chart(ctx, {
      type: 'doughnut',
      data: data,
      options: options
    });
    $(document).ready(function() {
    $('.table-container').scroll(function() {
        // 첫 번째 행의 너비와 위치를 변경하여 고정시킴
        $('.walltr:first-child').css('transform', 'translateY(' + $(this).scrollTop() + 'px)');
    });
});
    
    var _chart = document.querySelector('.chart');
    var _chartBar = document.querySelectorAll('.chart-bar');
    var color = ['#9986dd','#fbb871','#bd72ac','#f599dc'] //색상
    var newDeg = []; //차트 deg

    function insertAfter(newNode, referenceNode) {
        referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
    }

    function chartLabel(){
    var _div = document.createElement('div');
    _div.className = 'chart-total';
    _div.innerHTML = `<span class="chart-total-num">Total:<br> 3,135</span>
                        <span class="chart-total-text1">Automobile</span>
                        <span class="chart-total-text2">Disablility</span>
                        <span class="chart-total-text3">Life</span>
                        <span class="chart-total-text4">Property</span>`;
    insertAfter(_div,_chart);
    }

    function chartDraw(){ 
    for( var i=0;i<_chartBar.length;i++){
        var _num = _chartBar[i].dataset.deg
        newDeg.push( _num )
    }

    var num = newDeg.length - newDeg.length;
    _chart.style.background = 'conic-gradient(#9986dd '+
                                                    newDeg[num]+'deg, #fbb871 '+
                                                    newDeg[num]+'deg '+newDeg[num+1]+'deg, #bd72ac '+
                                                    newDeg[1]+'deg '+newDeg[2]+'deg, #f599dc '+
                                                    newDeg[2]+'deg )';
    
    chartLabel();
    }

    chartDraw();    
 
    /* 일정 행 추가 및 삭제 */
    var addBtnNum = 1; // 추가버튼
    var minusBtnNum = 1; // 삭제버튼

    // 일정 행 추가 함수
    function addRowBtn(button) {
        var table = document.getElementById('sortableList');
        var currentRow = button.parentNode.parentNode;
        var newRow = table.insertRow(currentRow.rowIndex + 1);
        addBtnNum++
        minusBtnNum++

        // 클래스 복사
        newRow.className = currentRow.className;
        // 행 복사
        newRow.innerHTML = `
            <td class="sctd plan_write_loca"><input type="text" placeholder="일정명" name="plan_sc_loca"></td>
            <td class="sctd plan_write_time"><input type="date" name="plan_sc_time"></td>
            <td class="sctd plan_write_adrr"><input type="text" placeholder="위치" name="plan_sc_addr"></td>
            <td class="sctd plan_write_memo"><textarea placeholder="여행에 대한 메모" name="plan_sc_memo"></textarea></td>
            <td class="sctd plan_write_type">
                <select id="plan_sc_type" name="plan_sc_type" class="rec_sc_select">
                    <option>경비종류선택</option>
                    <option value="교통비">교통비</option>
                    <option value="숙박비">숙박비</option>
                    <option value="식비">식비</option>
                    <option value="관광비">관광비</option>
                    <option value="기타">기타</option>
                </select>
            </td>
            <td class="sctd plan_write_cost"><input type="text" placeholder="경비금액" name="plan_sc_cost"></td>
            <td class="sctd plan_write_plus"><input type="button" value="+" id="addRowButton`+addBtnNum+`" onclick="addRowBtn(this)"></td>
            <td class="sctd plan_write_minus"><input type="button" value="-" id="minusRowButton`+minusBtnNum+`" onclick="minusRowBtn(`+minusBtnNum+`)"></td>
            <td class="sctd plan_write_moveRowBtn">
                <input class="moveRowUp" type="button" value="▲" onclick="moveRow(this.parentNode.parentNode, -1); highlightButton(this)">
                <br>
                <input class="moveRowDwon" type="button" value="▼" onclick="moveRow(this.parentNode.parentNode, 1); highlightButton(this)">    
            </td>
        `;
        // 입력 필드 초기화
        var inputs = newRow.querySelectorAll('input, textarea, select');
        inputs.forEach(input => {
            if (input.type === 'text' || input.type === 'textarea') {
                input.value = '';
            } else if (input.type === 'select-one') {
                input.selectedIndex = 0;
            }
        });
    }
    // 일정 행 삭제 함수
    function minusRowBtn(n){
       var deleteButtons = document.querySelectorAll("#minusRowButton"+n);
        var sctr = document.querySelectorAll('.sctr');
        // 각 삭제 버튼을 순회하며 이벤트 처리 추가
        deleteButtons.forEach(button => {
            // 삭제 버튼을 클릭한 행을 찾음
           //button.click();
            var row = button.closest(".sctr");
               if (sctr.length > 3) {
                  row.remove();
               }else{
                   alert("더 이상 삭제할 수 없습니다.");
               }
        });
    }
    /* 일정 행 이동 */
    function moveRow(row, direction) {
        var table = document.getElementById('sortableList');
        var currentIndex = row.rowIndex;
        var targetIndex = currentIndex + direction;
        if (targetIndex >= 1 && targetIndex < table.rows.length-1) {
            var targetRow = table.rows[targetIndex];

            // 저장된 버튼 정보 가져오기
            var plusButton = row.querySelector('.plan_write_plus input');
            var minusButton = row.querySelector('.plan_write_minus input');

            // 저장된 데이터 가져오기
            var locationInput = row.querySelector('.plan_write_loca input');
            var dateInput = row.querySelector('.plan_write_time input');
            var addressInput = row.querySelector('.plan_write_adrr input');
            var memoInput = row.querySelector('.plan_write_memo textarea');
            var typeSelect = row.querySelector('.plan_write_type select');
            var costInput = row.querySelector('.plan_write_cost input');

            // 행 옮기기
            table.deleteRow(currentIndex);
            table.insertRow(targetIndex);
            var newRow = table.rows[targetIndex];
            newRow.className = "sctr"; // 클래스 이름을 설정
            newRow.innerHTML = targetRow.innerHTML;

            // 행 옮길 때 색 변경 처리
            newRow.classList.add('highlighted-row'); // 변경될 행에 클래스 추가

            // 버튼 정보 복사
            newRow.querySelector('.plan_write_plus').innerHTML = plusButton.outerHTML;
            newRow.querySelector('.plan_write_minus').innerHTML = minusButton.outerHTML;

            // 데이터 설정
            newRow.querySelector('.plan_write_loca input').value = locationInput.value;
            newRow.querySelector('.plan_write_time input').value = dateInput.value;
            newRow.querySelector('.plan_write_adrr input').value = addressInput.value;
            newRow.querySelector('.plan_write_memo textarea').value = memoInput.value;
            newRow.querySelector('.plan_write_type select').value = typeSelect.value;
            newRow.querySelector('.plan_write_cost input').value = costInput.value;

            // 색 변경 원래대로 복구
            setTimeout(function() {
                newRow.classList.remove('highlighted-row');}, 500); // 0.5초 후에 클래스 제거
        }else if (direction === -1 && targetIndex < 1) {
            alert('더 이상 일정을 올릴 수 없습니다.');
        } else if (direction === 1 && targetIndex >= table.rows.length - 1) {
            alert('더 이상 일정을 내릴 수 없습니다.');
        }
    }
    // 일정 순서변경 시 버튼 하이라이트
    function highlightButton(input) {
        input.classList.add('btn-highlight'); 
        setTimeout(function() {
            input.classList.remove('btn-highlight');}, 500);
    }

    // 경비종류 미선택 시 선택 멘트 출력
    document.addEventListener("DOMContentLoaded", function() {
        const selectElement = document.getElementById("rec_sc_type");
        const placeholderOption = selectElement.querySelector("option");

        selectElement.addEventListener("change", function() {
            if (selectElement.value === "") {
                alert("경비종류를 선택해주세요.");
                selectElement.focus();
            }
        });
    });
   // 썸네일 사진 미리보기
    function readURL(input) {
        if(input.files && input.files[0]){
         var reader = new FileReader();
         reader.onload = function(e) {
             $('#imgx').attr('src', e.target.result);
         }
         reader.readAsDataURL(input.files[0]);
     }
   }
    $(function () {
      $('#mfile').change(function() {
          if($(this).val().length>0){
              readURL(this);
          }else{
              console.log("이미지없음");
          }
      })
   })