function rangeValueAge(){
    var rng=document.getElementById('age'); //rng - это Input
    var p=document.getElementById('one'); // p - абзац
    p.innerHTML="Возраст " + rng.value;
}
