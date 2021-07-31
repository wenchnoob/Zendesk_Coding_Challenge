const coll = document.getElementsByClassName("collapsible");
let i;
for (i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function(){
        this.classList.toggle("active");
        const content = this.nextElementSibling;
        if (content.style.maxHeight){
            content.style.maxHeight = null;
            this.innerHTML = this.innerHTML.replace("Collapse", "Expand");
        } else {
            content.style.maxHeight = content.scrollHeight + "px";
            this.innerHTML = this.innerHTML.replace("Expand", "Collapse");
        }
    });
}