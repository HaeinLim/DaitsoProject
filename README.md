# 다있소
회원이 원하는 상품을 조회하고 구매할 수 있으며 상품에 대한 문의, 구매후기를 남길 수 있는 온라인 쇼핑몰 웹 사이트입니다.

## 목차
  - [개요](#개요)
  - [프로그램 설명](#프로그램-설명)
  - [프로그램 화면](#프로그램-화면)
## 개요
  - 프로젝트명 : 다있소
  - 프로젝트 진행기간 : 2023.06.13-2023.07.07
  - 사용 기술 : JAVA, JSP, HTML, CSS, JavaScript, AJAX, JQuery, Oracle, SQL
  - 팀 구성원 : 임해인 외 4명

## 프로그램 설명
<div align="center">
  <table>
    <tr>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/4a0a4ce8-de63-4836-aa6f-85bd7a49eff4" width="600" height="400">
      </td>
    </tr>
    <tr>
      <td align="center">로그인 전 메인 페이지</td>
    </tr>
  </table>
</div>
코로나 팬데믹 이후 늘어난 비 대면 쇼핑과 증가한 온라인 시장 수요, 여러 요인으로 매장이 입점하지 못하는 지역의 존재에 따라 온라인 쇼핑몰 웹 사이트를 개발하게 되었습니다.

  - 회원가입하고 온라인 쇼핑하기 🛍️ <br>
    비회원은 메인 페이지에서 상품 판매글을 조회하고 상세 페이지까지 확인 가능하지만 구매와 문의 남기기는 불가능합니다. 이러한 기능을 활용하기 위해서는 회원가입을 진행해야하며
    아이디(중복체크 필수), 비밀번호, 비밀번호 확인, 이름, 전화번호, 이메일을 모두 조건에 맞게 입력해야만 회원가입이 가능합니다. 회원가입 한 아이디와 비밀번호를 통해
    로그인을 진행할 수 있습니다.
    
  - 상품 둘러보고 구매하기 😄💵<br>
    메인 페이지에서 관심있는 상품을 클릭하면 해당 상품의 상세 페이지로 이동합니다. 상세 페이지에는 상품의 상세 정보와 다른 회원들이 남긴 문의글 및 구매후기를 조회할 수 있습니다.
    만약 상품을 구매하고 싶다면 수량을 정한 후 장바구니에 담으면 장바구니에 담기게 됩니다. 수량은 장바구니에서 다시 조정할 수 있고 장바구니 내역을 삭제할 수도 있습니다.
    장바구니에 담긴 상품들 중 구매할 상품을 체크하면 지불할 총 금액이 결정되며, 결제하기를 클릭하면 배송주소를 입력하는 칸과 회원가입 때 입력한 이름, 전화번호, 이메일이 출력됩니다.
    주소를 입력한 후 결제하기 버튼을 클릭하면 결제창이 뜨며 결제를 진행할 수 있습니다.

  - 나를 위한 페이지, 마이 페이지 📃 <br>
    우측 상단의 마이 페이지를 클릭하면 이동할 수 있습니다. 이름은 보안을 위해 토큰을 사용하여 일부 가릴 수 있도록 설정하였고 장바구니, 내 주문목록보기, 내 문의글 보기,
    내 리뷰 보기, 결제 취소, 회원정보 수정이 가능합니다. 내 주문목록 보기에서는 회원이 구매한 내역을 확인할 수 있고, 여기서 구매한지 7일이 지나지 않았다면 구매후기를
    작성할 수 있습니다. 내 문의글 보기에선 회원이 작성한 문의글 내역을 조회할 수 있고, 내 리뷰 보기에선 회원에 작성한 구매후기 내역을 조회할 수 있습니다. 결제 취소에선
    결제가 완료된 내역 중 취소하고 싶은 게 있다면 결제 취소 신청을 할 수 있습니다. 회원 정보를 수정하기 위해선 비밀번호를 입력해 본인 인증을 하고, 아이디 외에 다른 정보를
    수정할 수 있습니다. 수정 페이지의 회원 탈퇴를 클릭하면 탈퇴가 진행되며 로그아웃 후 메인 페이지로 이동합니다.

  - 관리자 페이지 📃 <br>
    관리자 계정으로 로그인하면 우측 상단에 관리자 페이지로 이동할 수 있는 링크가 생깁니다. 관리자 페이지에선 회원/상품/문의글 조회가 가능하고 관리자의 정보를 수정할 수 있습니다.
    회원 조회에선 모든 회원의 정보를 조회할 수 있으나 비밀번호는 보안을 위해 *로 처리됩니다. 특정 회원의 이름을 검색해 해당 회원의 정보만 조회가 가능하고 회원을 삭제할 수 있습니다.
    상품 조회에선 새 상품을 등록할 수 있고 상품의 상세 설명을 등록하거나 수정할 수 있으며 상품을 수정할 수도 있습니다. 상품의 이름을 검색해 특정 상품의 정보만을 조회할 수 있고
    상품을 등록하거나 수정하는 페이지의 카테고리 관리를 클릭하면 카테고리의 등록, 수정, 삭제가 가능합니다.
    문의글 조회에선 회원이 남긴 문의를 확인하고 답글을 작성할 수 있으며, 관리자 정보 수정은 회원 정보 수정과 동일한 로직으로 처리됩니다.

## 프로그램 화면

- 회원가입

<div align="center">
  <table align="center">
      <tr>
        <th>회원가입</th>
      </tr>
      <tr>
        <td>
          <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/76c19eac-1f4e-41eb-a6d8-84e4f8a2af4a" width="400" height="200">
        </td>
      </tr>
      <tr>
        <td>
          - 모든 항목을 입력한 후 회원가입 진행<br>
          - 아이디 중복 체크 필수
        </td>
      </tr>
  </table>
</div>

- 메인 페이지 및 상세 페이지

<div align="center">
  <table align="center">
    <tr>
      <th>메인 페이지</th><th>상세 페이지</th><th>문의</th><th>리뷰</th>
    </tr>
    <tr>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/4a0a4ce8-de63-4836-aa6f-85bd7a49eff4" width="400" height="200">
      </td>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/a222439a-f9b3-4a3c-a638-f268f0ff5813" width="400" height="200">
      </td>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/f69f6ea5-7eff-4a74-8287-a4638928593f" width="400" height="200">
      </td>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/8669573b-6c0c-41b5-9e2b-3631f5cde6da" width="400" height="200">
      </td>
    </tr>
    <tr>
      <td>
        - 카테고리 별 조회 및 검색 가능<br>
        - 4가지 조건으로 페이지 정렬 가능<br>
      </td>
      <td>
        - 상품 수량 선택 후 장바구니 담기 가능<br>
      </td>
      <td>
        - 상품에 대한 문의 작성 및 확인<br>
        - 회원 및 관리자는 답글 작성 가능<br>
      </td>
      <td>
        - 회원이 작성한 리뷰 조회 가능<br>
        - 2가지 조건으로 정렬 가능
      </td>
    </tr>
  </table>
</div>

  - 장바구니 및 결제

<div align="center">
  <table align="center">
    <tr>
      <th>장바구니</th><th>결제</th>
    </tr>
    <tr>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/a26e75ca-3c4a-451a-8a23-e943ca09649b" width="400" height="200">
      </td>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/cb1f2ca3-2389-4a2d-b31d-35b648e7b398" width="400" height="200">
      </td>
    </tr>
    <tr>
      <td>
        - 최소 주문량은 1건, 최대 주문량은 7건<br>
        - 선택을 통해 부분 구매 및 삭제 가능
      </td>
      <td>
        - 주소 입력 후 정보 확인 후 결제 진행<br>
      </td>
    </tr>
  </table>
</div>

  - 마이 페이지 및 관리자 페이지

<div align="center">
  <table algin="center">
    <tr>
      <th>마이 페이지</th><th>관리자 페이지</th>
    </tr>
    <tr>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/ba8e7159-85fe-40d0-a86f-1ebf33c900cb" width="400" height="200">
      </td>
      <td>
        <img src="https://github.com/HaeinLim/DaitsoProject/assets/140698817/4f3badc5-67dd-4362-84ef-fcccafcf0c20" width="400" height="200">
      </td>
    </tr>
    <tr>
      <td>
        - 장바구니와 내 주문목록, 문의글, 리뷰, <br>
          결제 취소 및 정보 수정 가능
      </td>
      <td>
        - 회원조회, 상품 및 문의글 조회, 취소요청 관리,<br>
          관리자 정보 수정 가능<br>
        - 상품 조회에서 새 상품과 카테고리 등록 가능
      </td>
    </tr>
  </table>
</div>
