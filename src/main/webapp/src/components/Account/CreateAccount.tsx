const CreateAccount = () => {
    
  return (
    <div className="form-control w-full max-w-xs">
      <label className="label">
        <span className="label-text">Account Name</span>
      </label>
      <input
        type="text"
        placeholder="Type here"
        className="input input-bordered w-full max-w-xs"
      />
      <label className="label"></label>
      <label className="label">
        <span className="label-text">Starting Balance</span>
      </label>
      <input
        type="number"
        placeholder="1,000.00"
        className="input input-bordered w-full max-w-xs"
      />
      <label className="label"></label>
      <div className="input-bordered">
      <div className="form-control">
        <label className="label cursor-pointer">
          <span className="label-text">Budget account</span>
          <input
            type="radio"
            name="tracking"
            className="radio radio-primary"
            checked
          />
        </label>
      </div>
      <div className="form-control">
        <label className="label cursor-pointer">
          <span className="label-text">Tracking</span>
          <input
            type="radio"
            name="tracking"
            className="radio radio-primary"
            checked
          />
        </label>
        <button className="btn" type="button">Create</button>
      </div>
      </div>
    </div>
  );
};

export default CreateAccount;

{
  /* <div className="form-control w-full max-w-xs">
  <label className="label">
    <span className="label-text">What is your name?</span>
    <span className="label-text-alt">Top Right label</span>
  </label>
  <input type="text" placeholder="Type here" className="input input-bordered w-full max-w-xs" />
  <label className="label">
    <span className="label-text-alt">Bottom Left label</span>
    <span className="label-text-alt">Bottom Right label</span>
  </label>
</div> */
}
