// this 는 함수가 호출될 때 결정됨
const car = {
    name : 'KIA',
    getName:function (){
        console.log("car getName", this);
    }
};

// car.getName(); // Car 객체 호출됨

const globalCar = car.getName;
globalCar(); // window 객체 호출됨(Window 객체 : 자바스크립트에서 사용하는 내장 객체, 화면 관련)

// 차이점
/* car.getName() : car 객체 내부에 있는 함수 호출한 것 -> this 는 car 객체 그 자체
    globalCar() : 밖에서 호출됨, 여기서 this 는 최상단의 객체가 호출된 것
*
* */

const car2 = {
    name : 'hyundai',
    getName: car.getName
}

car2.getName() // car2 객체를 가리킬 것

const btn = document.getElementById('button');
btn.addEventListener('click', car.getName);