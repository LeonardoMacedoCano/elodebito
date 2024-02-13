import styled from 'styled-components';

export const Container = styled.div`
    background-color: ${props => props.theme.colors.tertiary};
    margin: 0;
    padding: 0;
`;

export const Body = styled.div`
    margin: auto;
    max-width: 800px;
    padding-top: 50px;
    padding: 25px 0 10px 0;
`;

export const DebitoArea = styled.div`
    background-color: ${props => props.theme.colors.white};
    box-shadow: 0px 0px 5px ${props => props.theme.colors.gray};
    border-radius: 10px;
    padding: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
`;

export const Column = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
`;

export const FirstRow = styled.div`
    text-align: center;
    font-weight: bold;
    color: ${props => props.theme.colors.tertiary};
    margin-bottom: 5px;
`;

export const SecondRow = styled.div`
    text-align: center;
    font-weight: bold;
    color: ${props => props.theme.colors.black};
    width: 100%;
    min-width: 120px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
`;

export const Label = styled.label`
    font-weight: bold;
    color: ${props => props.theme.colors.gray};
`;

export const Input = styled.input`
    width: 100%;
    height: 30px;
    padding: 0 5px;
    margin: 0 5px;
    border: 1px solid ${props => props.theme.colors.tertiary};
    border-radius: 5px;
`;

export const Button = styled.button`
    background-color: ${props => props.theme.colors.tertiary};
    color: ${props => props.theme.colors.white};
    width: 100%;
    height: 30px;
    padding: 0 5px;
    border: 1px solid ${props => props.theme.colors.white};
    border-radius: 5px;
`;
